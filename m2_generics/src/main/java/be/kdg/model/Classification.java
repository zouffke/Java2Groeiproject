package be.kdg.model;

/**
 * The Classification enum represents the different classifications of sailboats.
 * It is used to categorize sailboats based on their design and intended use.
 * The classifications include DAYSAILER, SPORTBOAT, Y, CRUISER, RACEBOAT, CRUISE_RACER, SLOOP, and UNKNOWN.
 */
public enum Classification {
    DAYSAILER, // A small sailboat used for casual sailing on days of good weather.
    SPORTBOAT, // A small, fast sailboat, used primarily for racing.
    Y, // A classification for yachts, typically larger, more luxurious sailboats.
    CRUISER, // A sailboat designed for distance sailing with comfortable accommodations for passengers.
    RACEBOAT, // A sailboat designed specifically for racing, often with minimal accommodations to save weight.
    CRUISE_RACER, // A sailboat that is a hybrid between a cruiser and a raceboat, offering speed and comfort.
    SLOOP, // A type of sailboat characterized by a single mast and two sails: a mainsail and a headsail.
    UNKNOWN // A classification for sailboats that do not fit into any of the other categories.
}