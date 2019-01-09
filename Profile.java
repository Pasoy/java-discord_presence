import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Profile {
	
	private StringProperty details = new SimpleStringProperty();
	private StringProperty state = new SimpleStringProperty();

	private StringProperty partyActivated = new SimpleStringProperty();
	private IntegerProperty partySize = new SimpleIntegerProperty();
	private IntegerProperty partyMax = new SimpleIntegerProperty();

	private StringProperty largeImageText = new SimpleStringProperty();
	private StringProperty largeImageKey = new SimpleStringProperty();
	private StringProperty smallImageText = new SimpleStringProperty();
	private StringProperty smallImageKey = new SimpleStringProperty();
	
	private StringBinding displayName = (StringBinding) details.concat(" - ").concat(state);
	
	public Profile() {
		this("Programming", "So go away", "No", "6", "9", "", "programming", "", "");
	}
	
	public Profile(String details, String state, String partyActivated, String partySize, String partyMax, String largeImageText, String largeImageKey, String smallImageText, String smallImageKey) {
		setDetails(details);
		setState(state);

		setPartyActivated(partyActivated);
		setPartySize(Integer.valueOf(partySize));
		setPartyMax(Integer.valueOf(partyMax));
		
		setLargeImageText(largeImageText);
		setLargeImageKey(largeImageKey);
		setSmallImageText(smallImageText);
		setSmallImageKey(smallImageKey);
	}
	
	@Override
	public String toString() {
		return getDetails() + ";" + getState() + ";" + getPartySize() + ";" + getPartyMax() + ";" + getLargeImageText() + ";" + getLargeImageKey() + ";" + getSmallImageText() + ";" + getSmallImageKey();
	}
	
	/*
	 * -------------------
	 *       DETAILS
	 * -------------------
	 */
	
	public StringProperty detailsProperty() {
		return this.details;
	}
	
	public String getDetails() {
		return this.detailsProperty().get();
	}
	
	public void setDetails(String details) {
		this.detailsProperty().set(details);
	}
	
	/*
	 * -------------------
	 *       STATE
	 * -------------------
	 */
	
	public StringProperty stateProperty() {
		return this.state;
	}
	
	public String getState() {
		return this.stateProperty().get();
	}
	
	public void setState(String state) {
		this.stateProperty().set(state);
	}
	
	/*
	 * -------------------
	 *   PARTYACTIVATED
	 * -------------------
	 */
	
	public StringProperty partyActivatedProperty() {
		return this.partyActivated;
	}
	
	public String getPartyActivated() {
		return this.partyActivatedProperty().get();
	}
	
	public void setPartyActivated(String partyActivated) {
		this.partyActivatedProperty().set(partyActivated);
	}
	
	/*
	 * -------------------
	 *      PARTYSIZE
	 * -------------------
	 */
	
	public IntegerProperty partySizeProperty() {
		return this.partySize;
	}
	
	public Integer getPartySize() {
		return this.partySizeProperty().get();
	}
	
	public void setPartySize(Integer partySize) {
		this.partySizeProperty().set(partySize);
	}
	
	/*
	 * -------------------
	 *      PARTYSIZE
	 * -------------------
	 */
	
	public IntegerProperty partyMaxProperty() {
		return this.partyMax;
	}
	
	public Integer getPartyMax() {
		return this.partyMaxProperty().get();
	}
	
	public void setPartyMax(Integer partyMax) {
		this.partyMaxProperty().set(partyMax);
	}
	
	/*
	 * -------------------
	 *   LARGEIMAGETEXT
	 * -------------------
	 */
	
	public StringProperty largeImageTextProperty() {
		return this.largeImageText;
	}
	
	public String getLargeImageText() {
		return this.largeImageTextProperty().get();
	}
	
	public void setLargeImageText(String largeImageText) {
		this.largeImageTextProperty().set(largeImageText);
	}
	
	/*
	 * -------------------
	 *   LARGEIMAGEKEY
	 * -------------------
	 */
	
	public StringProperty largeImageKeyProperty() {
		return this.largeImageKey;
	}
	
	public String getLargeImageKey() {
		return this.largeImageKeyProperty().get();
	}
	
	public void setLargeImageKey(String largeImageKey) {
		this.largeImageKeyProperty().set(largeImageKey);
	}
	
	/*
	 * -------------------
	 *   LARGEIMAGETEXT
	 * -------------------
	 */
	
	public StringProperty smallImageTextProperty() {
		return this.smallImageText;
	}
	
	public String getSmallImageText() {
		return this.smallImageTextProperty().get();
	}
	
	public void setSmallImageText(String smallImageText) {
		this.smallImageTextProperty().set(smallImageText);
	}
	
	/*
	 * -------------------
	 *   LARGEIMAGEKEY
	 * -------------------
	 */
	
	public StringProperty smallImageKeyProperty() {
		return this.smallImageKey;
	}
	
	public String getSmallImageKey() {
		return this.smallImageKeyProperty().get();
	}
	
	public void setSmallImageKey(String smallImageKey) {
		this.smallImageKeyProperty().set(smallImageKey);
	}
	
	/*
	 * -------------------
	 *     OTHER STUFF
	 * -------------------
	 */
	
	public StringBinding getDisplayName() {
		return displayName;
	}

}
