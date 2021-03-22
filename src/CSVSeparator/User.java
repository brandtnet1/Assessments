package CSVSeparator;

@SuppressWarnings("preview")
public record User (
	String userId,
	String firstName,
	String lastName,
	int version,
	String insuranceCompany
) {
	public String toCSV() {
		return userId() + "," + firstName() + "," + lastName() + "," + version() + "," + insuranceCompany() + "\n";
	}
}
