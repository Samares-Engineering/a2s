package org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary;

import org.eclipse.papyrus.activitysimulation.mokaconnector.continuouslibrary.mathfunctions.Sin;
import org.eclipse.papyrus.moka.fuml.Semantics.Loci.LociL1.Locus;
import org.eclipse.papyrus.moka.fuml.registry.AbstractOpaqueBehaviorExecutionRegistry;


public class ContinuousLibraryRegistry extends AbstractOpaqueBehaviorExecutionRegistry {

	protected final static String CONTINUOUS_LIBRARY_NAME = "ContinuousLibrary";

	@Override
	public void registerOpaqueBehaviorExecutions(Locus locus) {
		this.locus = locus;
		this.buildOpaqueBehaviorsMap(CONTINUOUS_LIBRARY_NAME);
		try {
			// ////////////////////
			// Math functions
			
			// Sin
			this.registerOpaqueBehaviorExecution(new Sin(), "ContinuousLibrary::MathFunctions::Sin");
			
		} catch (Exception e) {
			org.eclipse.papyrus.infra.core.Activator.log.error(e);
		}
	}
}
