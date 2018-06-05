package org.interior.vo;

public class SummonerSpell {
	
	String spell_1;
	String spell_2;
	
	public SummonerSpell(String sp1, String sp2) {
		// TODO Auto-generated constructor stub
		spell_1 = sp1;
		spell_2 = sp2;
	}
	
	public SummonerSpell() {
	}
	
	

	


	public String getSpell_1() {
		return spell_1;
	}

	public void setSpell_1(String spell_1) {
		this.spell_1 = spell_1;
	}

	public String getSpell_2() {
		return spell_2;
	}

	public void setSpell_2(String spell_2) {
		this.spell_2 = spell_2;
	}

	@Override
	public String toString() {
		return "SummonerSpell [spell_1=" + spell_1 + ", spell_2=" + spell_2 + "]";
	}
	
	
	
}
