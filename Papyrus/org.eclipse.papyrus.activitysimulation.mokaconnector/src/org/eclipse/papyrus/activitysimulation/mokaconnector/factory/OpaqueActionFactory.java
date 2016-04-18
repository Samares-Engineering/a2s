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
package org.eclipse.papyrus.activitysimulation.mokaconnector.factory;

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
