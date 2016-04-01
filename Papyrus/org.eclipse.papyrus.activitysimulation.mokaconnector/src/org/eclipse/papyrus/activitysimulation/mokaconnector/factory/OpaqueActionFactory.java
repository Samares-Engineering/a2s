package org.eclipse.papyrus.activitysimulation.mokaconnector.factory;

import org.eclipse.papyrus.moka.composites.Semantics.Actions.CompleteActions.CS_ReadExtentActionActivation;
import org.eclipse.papyrus.moka.composites.Semantics.Loci.LociL3.CS_ExecutionFactory;
import org.eclipse.papyrus.moka.fuml.Semantics.Loci.LociL1.SemanticVisitor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;

public class OpaqueActionFactory extends CS_ExecutionFactory {

	@Override
	public SemanticVisitor instantiateVisitor(Element element) {
		SemanticVisitor visitor = null;

		if(element instanceof OpaqueAction){
			visitor = new OpaqueActionActivation();
		} else {
			visitor = super.instantiateVisitor(element);
		}
		return visitor;
	}

}
