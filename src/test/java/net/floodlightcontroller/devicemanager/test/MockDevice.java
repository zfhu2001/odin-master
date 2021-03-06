/**
*    Copyright 2011,2012 Big Switch Networks, Inc. 
*    Originally created by David Erickson, Stanford University
* 
*    Licensed under the Apache License, Version 2.0 (the "License"); you may
*    not use this file except in compliance with the License. You may obtain
*    a copy of the License at
*
*         http://www.apache.org/licenses/LICENSE-2.0
*
*    Unless required by applicable law or agreed to in writing, software
*    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
*    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
*    License for the specific language governing permissions and limitations
*    under the License.
**/

package net.floodlightcontroller.devicemanager.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import net.floodlightcontroller.devicemanager.IEntityClass;
import net.floodlightcontroller.devicemanager.SwitchPort;
import net.floodlightcontroller.devicemanager.internal.Device;
import net.floodlightcontroller.devicemanager.internal.DeviceManagerImpl;
import net.floodlightcontroller.devicemanager.internal.Entity;

/**
 * This mock device removes the dependency on topology and a parent device
 * manager and simply assumes all its entities are current and correct
 */
public class MockDevice extends Device {

    public MockDevice(DeviceManagerImpl deviceManager,
                      Long deviceKey,
                      Entity entity, 
                      Collection<IEntityClass> entityClasses)  {
        super(deviceManager, deviceKey, entity, entityClasses);
    }

    public MockDevice(Device device, Entity newEntity,
                      Collection<IEntityClass> entityClasses) {
        super(device, newEntity, entityClasses);
    }
    
    public MockDevice(DeviceManagerImpl deviceManager, Long deviceKey,
                      Collection<Entity> entities,
                      IEntityClass[] entityClasses) {
        super(deviceManager, deviceKey, entities, entityClasses);
    }

    @Override
    public Integer[] getIPv4Addresses() {
        TreeSet<Integer> vals = new TreeSet<Integer>();
        for (Entity e : entities) {
            if (e.getIpv4Address() == null) continue;
            vals.add(e.getIpv4Address());
        }
        
        return vals.toArray(new Integer[vals.size()]);
    }

    @Override
    public SwitchPort[] getAttachmentPoints() {
        ArrayList<SwitchPort> vals = 
                new ArrayList<SwitchPort>(entities.length);
        for (Entity e : entities) {
            if (e.getSwitchDPID() != null &&
                e.getSwitchPort() != null) {
                SwitchPort sp = new SwitchPort(e.getSwitchDPID(), 
                                               e.getSwitchPort());
                vals.add(sp);
            }
        }
        return vals.toArray(new SwitchPort[vals.size()]);
    }

}
