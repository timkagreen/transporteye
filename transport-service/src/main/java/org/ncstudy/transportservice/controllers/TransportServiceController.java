package org.ncstudy.transportservice.controllers;


import org.ncstudy.transportservice.model.Transport;
import org.ncstudy.transportservice.services.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="api/v1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TransportServiceController {
	@Autowired
    private TransportService transportService;
    private static final Logger logger = LoggerFactory.getLogger(TransportServiceController.class);

    
	@RequestMapping(value="/transport",method = RequestMethod.GET)
    public List<Transport> getAllTransports() {
        logger.debug("Looking up data for transports...");
        List<Transport> list = transportService.getAllTransports();
        logger.debug("Found {} transport entries.", list.size());
        return list;
    }

    @RequestMapping(value="/transport",method = RequestMethod.POST)
    public void saveOrganization(@RequestBody Transport transport) {
       transportService.saveTransport(transport);
    }
    
    @RequestMapping(value="/transport",method = RequestMethod.PUT)
    public void updateOrganization(@RequestBody Transport transport) {
        transportService.saveTransport(transport);

    }

    @RequestMapping(value="/transport/{transportId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization( @PathVariable("transportId") UUID transportId) {
        transportService.deleteTransport( transportId );
    }
}
