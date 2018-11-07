package edu.gatech.cs2340.group75.donationtracker.Model;

public enum LocationType {
        DR ("Drop Off"),
        ST ("Store"),
        WA ("Warehouse");

        private final String type;

        LocationType(String type) {
            this.type = type;
        }

    /**
     * getter method for enum
     * @return string type of location
     */
    public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }

    /**
     * constructor for LocationType enum
     * @param text enum label
     * @return LocationType object
     */
		public static LocationType fromString(String text) {
			for (LocationType locationType : LocationType.values()) {
				if (locationType.type.equalsIgnoreCase(text)) {
					return locationType;
				}
			}
			
			return null;
		}

}
