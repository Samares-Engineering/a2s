/*******************************************************************************
 * Copyright (c) 2016 Samares-Engineering.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jean-Marie Gauthier - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary;

import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.ACos;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.ASin;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.ATan;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Cos;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Cosh;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Exp;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Log;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Log10;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Power;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Sin;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Sinh;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Sqrt;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Tan;
import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Tanh;
import org.eclipse.papyrus.moka.fuml.Semantics.Loci.LociL1.Locus;
import org.eclipse.papyrus.moka.fuml.registry.AbstractOpaqueBehaviorExecutionRegistry;


public class ContinuousLibraryRegistry extends AbstractOpaqueBehaviorExecutionRegistry {

	protected final static String CONTINUOUS_LIBRARY_NAME = "Continuous Library";

	@Override
	public void registerOpaqueBehaviorExecutions(Locus locus) {
		this.locus = locus;
		this.buildOpaqueBehaviorsMap(CONTINUOUS_LIBRARY_NAME);
		try {
			// ////////////////////
			// Math functions

			// ACos
			this.registerOpaqueBehaviorExecution(new ACos(), "ContinuousLibrary::MathFunctions::ACos");

			// ASin
			this.registerOpaqueBehaviorExecution(new ASin(), "ContinuousLibrary::MathFunctions::ASin");

			// ATan
			this.registerOpaqueBehaviorExecution(new ATan(), "ContinuousLibrary::MathFunctions::ATan");

			// Cos
			this.registerOpaqueBehaviorExecution(new Cos(), "ContinuousLibrary::MathFunctions::Cos");

			// Cosh
			this.registerOpaqueBehaviorExecution(new Cosh(), "ContinuousLibrary::MathFunctions::Cosh");

			// Exp
			this.registerOpaqueBehaviorExecution(new Exp(), "ContinuousLibrary::MathFunctions::Exp");

			// Log
			this.registerOpaqueBehaviorExecution(new Log(), "ContinuousLibrary::MathFunctions::Log");

			// Log10
			this.registerOpaqueBehaviorExecution(new Log10(), "ContinuousLibrary::MathFunctions::Log10");

			// Power
			this.registerOpaqueBehaviorExecution(new Power(), "ContinuousLibrary::MathFunctions::Power");

			// Sin
			this.registerOpaqueBehaviorExecution(new Sin(), "ContinuousLibrary::MathFunctions::Sin");

			// Sinh
			this.registerOpaqueBehaviorExecution(new Sinh(), "ContinuousLibrary::MathFunctions::Sinh");

			// Sqrt
			this.registerOpaqueBehaviorExecution(new Sqrt(), "ContinuousLibrary::MathFunctions::Sqrt");

			// Tan
			this.registerOpaqueBehaviorExecution(new Tan(), "ContinuousLibrary::MathFunctions::Tan");

			// Tanh
			this.registerOpaqueBehaviorExecution(new Tanh(), "ContinuousLibrary::MathFunctions::Tanh");

		} catch (Exception e) {
			org.eclipse.papyrus.infra.core.Activator.log.error(e);
		}
	}
}
