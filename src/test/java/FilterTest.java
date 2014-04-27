import org.junit.* ;
import static org.junit.Assert.* ;

import com.hbm.devices.scan.AnnouncePath;
import com.hbm.devices.scan.filter.JsonFilter;
import com.hbm.devices.scan.filter.Filter;
import com.hbm.devices.scan.filter.FamilytypeMatch;
import com.hbm.devices.scan.FakeStringMessageMulticastReceiver;
import com.hbm.devices.scan.messages.Announce;
import java.util.Observable;
import java.util.Observer;

public class FilterTest {

	private AnnouncePath ap;
	private FakeStringMessageMulticastReceiver fsmmr;

	@Before
	public void setup() {
		fsmmr = new FakeStringMessageMulticastReceiver();
		JsonFilter jf = new JsonFilter();
		fsmmr.addObserver(jf);
		Filter ftFilter = new Filter(new FamilytypeMatch("QuantumX"));
		jf.addObserver(ftFilter);
		ftFilter.addObserver(new Observer(){
			public void update(Observable o, Object arg) {
				ap = (AnnouncePath)arg;
			}
		});
	}

	@Test
	public void parseMissingFamilyTypeMessage() {
		fsmmr.emitMissingFamilyTypeMessage();
		assertTrue(ap == null);
	}
}
