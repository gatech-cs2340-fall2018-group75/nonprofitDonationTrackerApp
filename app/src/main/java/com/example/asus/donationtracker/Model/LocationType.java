package com.example.asus.donationtracker.Model;

public enum LocationType {
        DR ("Drop Off"),
        ST ("Store"),
        WA ("Warehouse");

        private final String type;

        LocationType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public String toString() {
            return type;
        }
		
		
		public static LocationType fromString(String text) {
			for (LocationType locationType : LocationType.values()) {
				if (locationType.type.equalsIgnoreCase(text)) {
					return locationType;
				}
			}
			
			return null;
		}

        //TODO:Fill in with actual location types
}
