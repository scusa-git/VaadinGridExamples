package vaadin.grid.examples.ui.component.grid;

public class MyBean {
	
	private String sign;
	private int value;
	
	public MyBean(int value) {
		this.value = value;
		this.sign = String.valueOf(Character.forDigit(value, 10));
	}

	public String getSign() {
		return sign;
	}

	public int getValue() {
		return value;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
}
