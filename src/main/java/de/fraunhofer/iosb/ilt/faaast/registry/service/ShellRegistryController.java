/*
 * Copyright (c) 2021 Fraunhofer IOSB, eine rechtlich nicht selbstaendige
 * Einrichtung der Fraunhofer-Gesellschaft zur Foerderung der angewandten
 * Forschung e.V.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.iosb.ilt.faaast.registry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.iosb.ilt.faaast.registry.core.exception.ResourceAlreadyExistsException;
import de.fraunhofer.iosb.ilt.faaast.registry.core.exception.ResourceNotFoundException;
import de.fraunhofer.iosb.ilt.faaast.service.model.descriptor.AssetAdministrationShellDescriptor;
import de.fraunhofer.iosb.ilt.faaast.service.model.descriptor.SubmodelDescriptor;


/**
 * REST controller for the Asset Administration Shell registry.
 */
@RestController
@RequestMapping("/registry/shell-descriptors")
public class ShellRegistryController {

    @Autowired
    RegistryService service;

    /**
     * Retrieves a list of all registered Asset Administration Shells.
     *
     * @return The list of all registered Asset Administration Shells.
     */
    @GetMapping()
    public List<AssetAdministrationShellDescriptor> getAASs() {
        return service.getAASs();
    }


    /**
     * Retrieves the Asset Administration Shell with the given ID.
     *
     * @param aasIdentifier The ID of the desired Asset Administration Shell.
     * @return The desired Asset Administration Shell.
     * @throws ResourceNotFoundException When the AAS was not found.
     */
    @GetMapping(value = "/{aasIdentifier}")
    public AssetAdministrationShellDescriptor getAAS(@PathVariable("aasIdentifier") String aasIdentifier) throws ResourceNotFoundException {
        return service.getAAS(aasIdentifier);
    }


    /**
     * Create the given Asset Administration Shell.
     *
     * @param resource The desired Asset Administration Shell.
     * @return The created Asset Administration Shell.
     * @throws ResourceAlreadyExistsException When the AAS already exists.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AssetAdministrationShellDescriptor create(@RequestBody AssetAdministrationShellDescriptor resource) throws ResourceAlreadyExistsException {
        return service.createAAS(resource);
    }


    /**
     * Deletes the Asset Administration Shell with the given ID.
     *
     * @param aasIdentifier The ID of the desired Asset Administration Shell.
     * @throws ResourceNotFoundException When the AAS was not found.
     */
    @DeleteMapping(value = "/{aasIdentifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("aasIdentifier") String aasIdentifier) throws ResourceNotFoundException {
        service.deleteAAS(aasIdentifier);
    }


    /**
     * Updates the given Asset Administration Shell.
     *
     * @param aasIdentifier The ID of the desired Asset Administration Shell.
     * @param aas The desired Asset Administration Shell.
     * @return The updated Asset Administration Shell.
     * @throws ResourceNotFoundException When the AAS was not found.
     */
    @PutMapping(value = "/{aasIdentifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AssetAdministrationShellDescriptor update(@PathVariable("aasIdentifier") String aasIdentifier,
                                                     @RequestBody AssetAdministrationShellDescriptor aas)
            throws ResourceNotFoundException {
        return service.updateAAS(aasIdentifier, aas);
    }


    /**
     * Retrieves a list of all Submodels of the given Asset Administration Shell.
     *
     * @param aasIdentifier The ID of the desired Asset Administration Shell.
     * @return The list of Submodels.
     * @throws ResourceNotFoundException When the AAS was not found.
     */
    @GetMapping(value = "/{aasIdentifier}/submodel-descriptors")
    public List<SubmodelDescriptor> getSubmodelsOfAAS(@PathVariable("aasIdentifier") String aasIdentifier) throws ResourceNotFoundException {
        return service.getSubmodels(aasIdentifier);
    }


    /**
     * Retrieves the Submodel with given AAS ID and Submodel ID.
     *
     * @param aasIdentifier The ID of the desired Asset Administration Shell.
     * @param submodelIdentifier The ID of the desired Submodel.
     * @return The desired Submodel.
     * @throws ResourceNotFoundException When the AAS or Submodel was not found.
     */
    @GetMapping(value = "/{aasIdentifier}/submodel-descriptors/{submodelIdentifier}")
    public SubmodelDescriptor getSubmodelOfAAS(@PathVariable("aasIdentifier") String aasIdentifier,
                                               @PathVariable("submodelIdentifier") String submodelIdentifier)
            throws ResourceNotFoundException {
        return service.getSubmodel(aasIdentifier, submodelIdentifier);
    }


    /**
     * Create a new submodel.
     *
     * @param aasIdentifier The ID of the desired AAS.
     * @param submodel The submodel to add.
     * @return The descriptor of the created submodel.
     * @throws ResourceNotFoundException When the AAS was not found.
     * @throws ResourceAlreadyExistsException When the Submodel already exists.
     */
    @PostMapping(value = "/{aasIdentifier}/submodel-descriptors")
    @ResponseStatus(HttpStatus.CREATED)
    public SubmodelDescriptor create(@PathVariable("aasIdentifier") String aasIdentifier,
                                     @RequestBody SubmodelDescriptor submodel)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return service.createSubmodel(aasIdentifier, submodel);
    }


    /**
     * Updates the given Submodel.
     *
     * @param aasIdentifier The ID of the desired AAS.
     * @param submodelIdentifier The ID of the desired Submodel.
     * @param submodel The desired Submodel.
     * @return The updated Submodel.
     * @throws ResourceNotFoundException When the AAS was not found.
     * @throws ResourceAlreadyExistsException When the Submodel already exists.
     */
    @PutMapping(value = "/{aasIdentifier}/submodel-descriptors/{submodelIdentifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SubmodelDescriptor updateSubmodelOfAAS(@PathVariable("aasIdentifier") String aasIdentifier,
                                                  @PathVariable("submodelIdentifier") String submodelIdentifier,
                                                  @RequestBody SubmodelDescriptor submodel)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return service.updateSubmodel(aasIdentifier, submodelIdentifier, submodel);
    }


    /**
     * Deletes the Submodel with the given ID.
     *
     * @param aasIdentifier The ID of the desired AAS.
     * @param submodelIdentifier The ID of the desired Submodel.
     * @throws ResourceNotFoundException When the Submodel was not found.
     */
    @DeleteMapping(value = "/{aasIdentifier}/submodel-descriptors/{submodelIdentifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubmodelOfAAS(@PathVariable("aasIdentifier") String aasIdentifier,
                                    @PathVariable("submodelIdentifier") String submodelIdentifier)
            throws ResourceNotFoundException {
        service.deleteSubmodel(aasIdentifier, submodelIdentifier);
    }
}
