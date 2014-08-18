package com.bendude56.goldenapple.permissions.audit;

import java.util.HashMap;

import com.bendude56.goldenapple.permissions.IPermissionGroup;
import com.bendude56.goldenapple.permissions.IPermissionUser;

public class GroupRemoveOwnerEvent extends PermissionEvent {
    public String groupName;
    public long groupId;
    
    public GroupRemoveOwnerEvent() {
        super(407, AuditEventLevel.SEVERE);
    }
    
    public GroupRemoveOwnerEvent(String authorizingUser, IPermissionUser target, IPermissionGroup group) {
        super(407, AuditEventLevel.SEVERE, authorizingUser, target);
        
        this.groupName = group.getName();
        this.groupId = group.getId();
    }
    
    @Override
    public String formatMessage() {
        return "User " + targetName + " (ID: " + targetId + ") has been removed as an owner of group " + groupName + " (ID: " + groupId + ") by " + authorizingUser;
    }
    
    @Override
    protected void loadMetadata(HashMap<String, AuditMetadata> metadata) {
        super.loadMetadata(metadata);
        
        groupName = metadata.get("groupName").valueString;
        groupId = metadata.get("groupId").valueInt;
    }
    
    @Override
    protected HashMap<String, AuditMetadata> saveMetadata() {
        HashMap<String, AuditMetadata> metadata = super.saveMetadata();
        
        metadata.put("groupName", createMetadata("groupName", groupName));
        metadata.put("groupId", createMetadata("groupId", groupId));
        
        return metadata;
    }
    
}