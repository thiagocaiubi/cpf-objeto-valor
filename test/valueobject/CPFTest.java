package valueobject;

import junit.framework.Assert;

import org.junit.Test;

import exception.InvalidCPFException;

public class CPFTest {

	private static final String VALID_FORMATTED_CPF = "123.123.123-87";
	private static final String VALID_NON_FORMATTED_CPF = "12312312387";

	@Test
	public void testCPF() {
		new CPF(VALID_FORMATTED_CPF);
		new CPF(VALID_NON_FORMATTED_CPF);
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidCPFFormatted() {
		new CPF("123.123.123-80");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidCPFNonFormatted() {
		new CPF("12312312380");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidCPFNonDigits() {
		new CPF("cpf");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidCPFEmpty() {
		new CPF("");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidCPFNull() {
		new CPF(null);
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFZero() throws Exception {
		new CPF("00000000000");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFOne() throws Exception {
		new CPF("11111111111");
	}

	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFTwo() throws Exception {
		new CPF("22222222222");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFThree() throws Exception {
		new CPF("33333333333");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFFour() throws Exception {
		new CPF("44444444444");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFFive() throws Exception {
		new CPF("55555555555");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFSix() throws Exception {
		new CPF("66666666666");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFSeven() throws Exception {
		new CPF("77777777777");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFEight() throws Exception {
		new CPF("88888888888");
	}
	
	@Test(expected=InvalidCPFException.class)
	public void testInvalidRepeatedDigitsCPFNine() throws Exception {
		new CPF("99999999999");
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals(VALID_FORMATTED_CPF, new CPF(VALID_NON_FORMATTED_CPF).toString());
	}
	
	@Test
	public void testToNonFormattedString() {
		Assert.assertEquals(VALID_NON_FORMATTED_CPF, new CPF(VALID_FORMATTED_CPF).toNonFormattedString());
	}


	@Test
	public void testHashCode() {
		Assert.assertEquals(new CPF(VALID_FORMATTED_CPF).hashCode(), new CPF(VALID_FORMATTED_CPF).hashCode());
	}
	
	@Test
	public void testEqualsObject() {
		CPF cpf = new CPF(VALID_FORMATTED_CPF);
		CPF other = new CPF("481.225.165-64");
		Assert.assertFalse(cpf.equals(other));
		Assert.assertFalse(cpf.equals(null));
		Assert.assertFalse(cpf.equals(VALID_FORMATTED_CPF));
		Assert.assertTrue(cpf.equals(cpf));
		Assert.assertTrue(new CPF(VALID_FORMATTED_CPF).equals(new CPF(VALID_FORMATTED_CPF)));
		Assert.assertTrue(new CPF(VALID_NON_FORMATTED_CPF).equals(new CPF(VALID_NON_FORMATTED_CPF)));
		Assert.assertTrue(new CPF(VALID_FORMATTED_CPF).equals(new CPF(VALID_NON_FORMATTED_CPF)));
	}
}