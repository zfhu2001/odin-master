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

package net.floodlightcontroller.devicemanager;

import java.util.Date;

/**
 * Represents an independent device on the network.  A device consists of a 
 * set of entities, and all the information known about a given device comes
 * only from merging all associated entities for that device.
 * @author readams
 */
public interface IDevice {
    /**
     * Get the primary key for this device.
     * @return the primary key
     */
    public Long getDeviceKey();
    
    /**
     * Get the MAC address of the device as a Long value.
     * @return the MAC address for the device
     */
    public long getMACAddress();

    /**
     * Get the MAC address of the device as a String value.
     * @return the MAC address for the device
     */
    public String getMACAddressString();
    
    /**
     * Get all unique VLAN IDs for the device.  If the device has untagged 
     * entities, then the value -1 will be returned.
     * @return an array containing all unique VLAN IDs for the device.
     */
    public Short[] getVlanId();
    
    /**
     * Get all unique IPv4 addresses associated with the device.
     * @return an array containing the unique IPv4 addresses for the device.
     */
    public Integer[] getIPv4Addresses();
    
    /**
     * Get all unique attachment points associated with the device.  This will
     * not include any blocked attachment points.
     * @return an array containing all unique attachment points for the device
     */
    public SwitchPort[] getAttachmentPoints();
    
    /**
     * Get all unique attachment points associated with the device.
     * @param includeError whether to include blocked attachment points.
     * Blocked attachment points should not be used for forwarding, but
     * could be useful to show to a user
     * @return an array containing all unique attachment points for the device
     */
    public SwitchPort[] getAttachmentPoints(boolean includeError);
    
    /**
     * Get the most recent timestamp for this device
     * @return the last seen timestamp
     */
    public Date getLastSeen();
    
    /**
     * Get the entity classes for the device.
     * @return the entity classes
     * @see IEntityClassifier
     * @see IDeviceService#setEntityClassifier(IEntityClassifier)
     */
    public IEntityClass[] getEntityClasses();
}
