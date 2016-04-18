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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Object_;
import org.eclipse.papyrus.moka.fuml.registry.AbstractSystemServicesRegistry;
import org.eclipse.papyrus.moka.fuml.standardlibrary.library.io.StandardInputChannelImpl;
import org.eclipse.papyrus.moka.fuml.standardlibrary.library.io.StandardOutputChannelImpl;
import org.eclipse.uml2.uml.Class;

public class ContinuousServicesRegistry extends AbstractSystemServicesRegistry {

	protected final static String CONTINUOUS_LIBRARY_NAME = "Continuous Library";

	protected final static String STANDARD_INPUT_CHANNEL_SERVICE_NAME = "FoundationalModelLibrary::BasicInputOutput::StandardInputChannel";

	protected final static String STANDARD_OUTPUT_CHANNEL_SERVICE_NAME = "FoundationalModelLibrary::BasicInputOutput::StandardOutputChannel";

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.papyrus.moka.fuml.registry.AbstractSystemServicesRegistry#instantiateServices()
	 */
	@Override
	public List<Object_> instantiateServices() {
		List<String> serviceQualifiedNames = new ArrayList<String>();
		serviceQualifiedNames.add(STANDARD_INPUT_CHANNEL_SERVICE_NAME);
		serviceQualifiedNames.add(STANDARD_OUTPUT_CHANNEL_SERVICE_NAME);
		return this.instantiateServices(CONTINUOUS_LIBRARY_NAME, serviceQualifiedNames);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.papyrus.moka.fuml.registry.AbstractSystemServicesRegistry#instantiateService(org.eclipse.uml2.uml.Class)
	 */
	@Override
	protected Object_ instantiateService(Class service) {
		if (service.getQualifiedName().equals(STANDARD_INPUT_CHANNEL_SERVICE_NAME)) {
			return new StandardInputChannelImpl(service);
		} else if (service.getQualifiedName().equals(STANDARD_OUTPUT_CHANNEL_SERVICE_NAME)) {
			return new StandardOutputChannelImpl(service);
		}
		return null;
	}

}
