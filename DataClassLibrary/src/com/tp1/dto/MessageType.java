package com.tp1.dto;

public enum MessageType {
	ThreadPerObject("a"),
	ThreadPerRequest("b"),
    GetMatch("c"),
    GetMatchs("d"),
    GetPari("e"),
    SetPari("f"),
    GetCompteursForAMatch("g"),
    GetPenalitesForAMatch("h"),
    GetTimeForAMatch("i"),
    GetAllCompteurs("j"),
    GetAllPenalites("k");
	
	private String value;
	
	MessageType(String value)
	{
	    this.value = value;
	}
	
	public String getValue() {
        return value;
    }
}
