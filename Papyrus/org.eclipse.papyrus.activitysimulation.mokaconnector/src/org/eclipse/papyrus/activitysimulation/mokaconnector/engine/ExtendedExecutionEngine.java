package org.eclipse.papyrus.activitysimulation.mokaconnector.engine;

import org.eclipse.papyrus.activitysimulation.mokaconnector.factory.OpaqueActionFactory;
import org.eclipse.papyrus.moka.composites.CompositeStructuresExecutionEngine;
import org.eclipse.papyrus.moka.composites.Semantics.CommonBehaviors.Communications.CS_DispatchOperationOfInterfaceStrategy;
import org.eclipse.papyrus.moka.composites.Semantics.CommonBehaviors.Communications.CS_NameBased_StructuralFeatureOfInterfaceAccessStrategy;
import org.eclipse.papyrus.moka.composites.Semantics.CompositeStructures.InvocationActions.CS_DefaultConstructStrategy;
import org.eclipse.papyrus.moka.composites.Semantics.CompositeStructures.InvocationActions.CS_DefaultRequestPropagationStrategy;
import org.eclipse.papyrus.moka.composites.Semantics.Loci.LociL3.CS_ExecutionFactory;
import org.eclipse.papyrus.moka.composites.Semantics.Loci.LociL3.CS_Executor;
import org.eclipse.papyrus.moka.composites.Semantics.Loci.LociL3.CS_Locus;
import org.eclipse.papyrus.moka.fuml.FUMLExecutionEngineForMoka;
import org.eclipse.papyrus.moka.fuml.Semantics.Loci.LociL1.Locus;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.OpaqueAction;

public class ExtendedExecutionEngine extends CompositeStructuresExecutionEngine {

	
	@Override
	public void start(Behavior behavior) {
		if (behavior != null) {

			main = behavior;

			// creates the locus, executor and execution factory
			this.locus = new CS_Locus();
			locus.setExecutor(new CS_Executor());
			locus.setFactory(new OpaqueActionFactory());

			// initializes built-in primitive types
			this.initializeBuiltInPrimitiveTypes(locus);

			// Initializes opaque behavior executions
			this.registerOpaqueBehaviorExecutions(locus);

			// Initializes semantic strategies
			this.registerSemanticStrategies(locus);

			// Initializes system services
			this.registerSystemServices(locus);

			// Initializes arguments
			this.initializeArguments(this.args);

			// Finally launches the execution
			this.started = true;

			// Finally launches the execution
			locus.executor.execute(main, null, this.arguments);
		}
		
	}
}
