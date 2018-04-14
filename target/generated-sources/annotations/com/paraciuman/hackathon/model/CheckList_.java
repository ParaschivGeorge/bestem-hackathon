package com.paraciuman.hackathon.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CheckList.class)
public abstract class CheckList_ {

	public static volatile SingularAttribute<CheckList, Boolean> checked;
	public static volatile SingularAttribute<CheckList, String> comment;
	public static volatile SingularAttribute<CheckList, Long> id;
	public static volatile SingularAttribute<CheckList, User> user;

}

