package ID318783479_ID316334473;

import java.util.Arrays;

public class PartyRegistry {
	// Constants

	// Fields
	private Party[] partyRegistry;
	private int partyCount;

	// Properties (Getters and Setters)
	public Party[] getPartyRegistry() {
		return partyRegistry;
	}
	
	public void setPartyRegistry(Party[] partyRegistry) {
		this.partyRegistry = partyRegistry;
	}

	public int getPartyCount() {
		return partyCount;
	}

	private void setPartyCount(int partyCount) {
		this.partyCount = partyCount;
	}

	// Constructors
	public PartyRegistry() {
		setPartyRegistry(new Party[Elections.MAX_ARRAY_SIZE]);
		setPartyCount(0);
	}

	// Methods
	public Party get(String partyName) {
		return (indexOf(partyName) == -1) ? null : partyRegistry[indexOf(partyName)];
	}

	public Party get(int index) {
		return (0 <= index && index <= partyCount) ? partyRegistry[index] : null;
	}
	
	public int indexOf(String partyName) {
		return indexOf(0, partyCount - 1, partyName);
	}

	private int indexOf(int leftIndex, int rightIndex, String partyName) {
		int mid;

		if (rightIndex >= leftIndex) {
			mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (partyRegistry[mid].getName().equalsIgnoreCase(partyName))
				return mid;
			if (partyRegistry[mid].getName().compareTo(partyName) > 0)
				return indexOf(leftIndex, mid - 1, partyName);

			return indexOf(mid + 1, rightIndex, partyName);
		}

		return -1;
	}

	public boolean add(Party party) {
		int i;

		// Validations
		if (partyCount == Elections.MAX_ARRAY_SIZE)
			return false;
		if (partyCount == 0) {
			partyRegistry[partyCount] = party;
			partyCount++;

			return true;
		}
		if (indexOf(party.getName()) != -1)
			return false;

		i = partyCount;
		while ((i > 0) && (partyRegistry[i - 1].getName().compareTo(party.getName()) > 0)) {
			partyRegistry[i] = partyRegistry[i - 1];
			i--;
		}
		partyRegistry[i] = party;
		partyCount++;

		return true;
	}
	
	public boolean remove(String partyName) {
		int partyOffset = indexOf(partyName), i;

		// Validations
		if (partyCount == 0)
			return false;
		if (partyOffset == -1)
			return false;

		partyRegistry[partyOffset] = null;
		i = partyOffset;
		while ((i > 0) && (partyRegistry[i - 1].getName().compareTo(partyRegistry[i + 1].getName()) > 0)) {
			partyRegistry[i] = partyRegistry[i + 1];
			i++;
		}
		partyCount--;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartyRegistry other = (PartyRegistry) obj;
		return partyCount == other.partyCount && Arrays.equals(partyRegistry, other.partyRegistry);
	}

	@Override
	public String toString() {
		StringBuilder sb;
		
		if (partyCount == 0)
			return "Nothing to See here..";

		sb = new StringBuilder();
		for (int i = 0; i < partyCount; i++)
			sb.append(partyRegistry[i].toString() + "\n");

		return sb.toString();
	}
}
