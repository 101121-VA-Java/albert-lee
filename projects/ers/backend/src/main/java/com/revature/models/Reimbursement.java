package com.revature.models;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Reimbursement implements Serializable {

    private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    private int id;
    private int amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private InputStream image;
    private String description;
    private int authorId;
    private int resolverId;
    private int statusId;
    private int typeId;
    private Boolean hasImage=false;

    public Reimbursement() {
    }

    public Reimbursement(int id, int amount, Timestamp submitted, Timestamp resolved, String description, int authorId, int resolverId, int statusId, int typeId) {
        this.id = id;
        this.amount = amount;
        this.setSubmitted(submitted);
        this.setResolved(resolved);
        this.description = description;
        this.authorId = authorId;
        this.setResolverId(resolverId);
        this.setStatusId(statusId);
        this.typeId = typeId;
    }

    public Reimbursement(int id, int amount, Timestamp submitted, Timestamp resolved, String description, int authorId,
            int resolverId, int statusId, int typeId, Boolean hasImage) {
        this.id = id;
        this.amount = amount;
        this.setSubmitted(submitted);
        this.setResolved(resolved);
        this.description = description;
        this.authorId = authorId;
        this.setResolverId(resolverId);
        this.setStatusId(statusId);
        this.typeId = typeId;
        this.hasImage = hasImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getTypeId() {
        return typeId;
    }

    
    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(Boolean hasImage) {
        this.hasImage = hasImage;
    }
}
