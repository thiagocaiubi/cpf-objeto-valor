package valueobject;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InvalidCPFException;

public final class CPF implements Serializable {

	private static final long serialVersionUID = -2584913662658215043L;
	
	private static final String REPLACEMENT_FORMAT = "$1.$2.$3-$4";
	private static final Pattern NON_FORMATTED_PATTERN = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");

	private String cpf;
	private String formattedCpf;

	public CPF(String cpf) {
		cleanNonDigitsAndStoreIt(cpf);
		validateCheckDigits();
		validateRepeatedDigits();
		format();
	}

	private void cleanNonDigitsAndStoreIt(String cpf) {
		this.cpf = cpf.replaceAll("\\D", "").trim();
	}

	private void validateCheckDigits() {
		long cpf = Long.parseLong(this.cpf);
		long dv = cpf % 100;
		cpf /= 10;
		int d2 = 0;
		for (int i = 1; i <= 10; i++) {
			long x = cpf % (long) Math.pow(10, 11 - i);
			long y = x / (long) Math.pow(10, 10 - i);
			d2 += y * (i - 1);
		}
		d2 = (d2 % 11) % 10;
		cpf /= 10;
		int d1 = 0;
		for (int i = 1; i <= 9; i++) {
			long x = cpf % (long) Math.pow(10, 10 - i);
			long y = x / (long) Math.pow(10, 9 - i);
			d1 += y * i;
		}
		d1 = (d1 % 11) % 10;
		if (dv != (d1 * 10 + d2)) {
			throw new InvalidCPFException(this.cpf);
		}
	}

	private void validateRepeatedDigits() {
		String[] tokens = this.cpf.split("");
		for (int i = 1; i < tokens.length; i++) {
			if (!tokens[1].equals(tokens[i])) {
				return;
			}
		}
		throw new InvalidCPFException(this.cpf);
	}

	private void format() {
		Matcher matcher = NON_FORMATTED_PATTERN.matcher(cpf);
		this.formattedCpf = matcher.replaceAll(REPLACEMENT_FORMAT);
	}

	public String toNonFormattedString() {
		return cpf;
	}

	@Override
	public String toString() {
		return formattedCpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cpf.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CPF other = (CPF) obj;
		return cpf.equals(other.cpf);
	}
}