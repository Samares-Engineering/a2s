clear;
clc;

loadXcosLibs();

importXcosDiagram('automatic_test.zcos');

w0 = 2*%pi*100;
m  = 0.5;
K0 = 0.1;

///////////////////////
// Optimization loop //
///////////////////////

MaxEvalFunc = 10;
Pfact  = 1;
Ifact  = 1;
h_step = 1e-3;  // the delta used to compute finite difference derivative
x0     = [1;5]; // Initial parameters
                // P is proportionnal to Pfact
                // I is proportionnal to Ifact
Lower = [0.01;0.01];
Upper = [1000; 100];

global Iter;
Iter = 0;

// Definition of the objective function

function y = f_pid(x)
  global Iter;
  %scicos_context.w0 = w0;
  %scicos_context.m  = m;
  %scicos_context.K0 = K0;
  %scicos_context.P  = x(1)*Pfact;
  %scicos_context.I  = x(2)*Ifact;
  Iter = Iter + 1;
  Info = scicos_simulate(scs_m,list(),%scicos_context,flag='nw');
  y_error = mean(abs((block_output('values')(:,1) -  block_output('values')(:,2))));
  y_diff  = mean(abs(diff(block_output('values')(:,2))));
  y = 0.5*y_error + 0.5*1*y_diff; ...
  printf('Evaluation %d - P = %f I = %f y = %f (y_error = %f, y_diff = %f)\n',Iter,x(1),x(2),y,y_error,y_diff);
endfunction

// Definition of the optim wrapper for the objective function

// Using 'derivative' for computing partial derivatives

function [f,df,ind] = my_optim_pid(x,ind)
  f  = f_pid(x);
  df = numderivative(f_pid, x, h_step, 4);
endfunction

// The error is a weighted sum beetwen the error and the derivative (the noise produced by the signal)

// Initial simulation for setting up everything (Info for example)

%scicos_context = [];
%scicos_context.w0 = w0;
%scicos_context.m  = m;
%scicos_context.K0 = K0;
%scicos_context.P = Pfact*x0(1);
%scicos_context.I = Ifact*x0(2);

Info = scicos_simulate(scs_m,list(),%scicos_context);
xs2png(gcf(), 'PID_Init.png')
      
[f_opt, x_opt] = optim(my_optim_pid,'b',Lower,Upper,x0,algo='qn','ar',MaxEvalFunc,MaxEvalFunc,1e-3,1e-3,[1e-3;1e-3]);

/////////////////////////////////
// Now test the solution found //
/////////////////////////////////

// We find a good solution, but it doesn't take into account the stability of the system.
%scicos_context.w0 = w0; ...
%scicos_context.m  = m; ...
%scicos_context.K0 = K0; ...
%scicos_context.P = x_opt(1)*Pfact;
%scicos_context.I = x_opt(2)*Ifact;

x_opt = [Pfact;Ifact].*x_opt;

Info = scicos_simulate(scs_m,Info,%scicos_context);
xs2png(gcf(), 'PID_Final.png')
