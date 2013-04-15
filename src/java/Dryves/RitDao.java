package Dryves;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 *
 * @author jeroen
 */
public class RitDao {

	static Connection currentCon;
	static ResultSet rs;
	int lidnr;
	String startpunt;
	String eindpunt;
	String waypoints;
	Double afstand;
	Double prijs;
	int gekocht;
	Timestamp datum;
	DateTime begindatum;
	DateTime einddatum;
	int zitplaatsen;
	int aangeboden;
	String brandstof;
	Boolean success;
	int ma = 0;
	int di = 0;
	int wo = 0;
	int don = 0;
	int vr = 0;
	int za = 0;
	int zo = 0;
	int meerdere = 0;

	/**
	 * Ophalen van alle gegevens uit de servlet voor de rit_plannen.jsp
	 *
	 * @param bean
	 * @return
	 */
	public Rit ritplannen(Rit bean) {

		setLidnr(bean.getLidnr());
		setStartpunt(bean.getStartpunt());
		setEindpunt(bean.getEindpunt());
		setWaypoints(bean.getWaypoint());
		setAfstand(bean.getAfstand());
		setPrijs(bean.getPrijs());
		setGekocht(bean.getGekocht());
		setDatum(bean.getDatum());
		//setEinddatum(bean.getEinddatum());

		setZitplaatsen(bean.getZitplaatsen());
		setAangeboden(bean.getAangeboden());
		setBrandstof(bean.getBrandstof());

		if (meerdere != 0) {
			saveMeerdereRitten();
		} else {
			saveRit();
		}
		
		return bean;
	}

	/**
	 * Opslaan van rit in de database
	 */
	private void saveMeerdereRitten() {
		ArrayList<Integer> dagenVdWeek = new ArrayList();
		if (ma == 1) {
			dagenVdWeek.add(ma);
		} else if (di == 2) {
			dagenVdWeek.add(di);
		} else if (wo == 3) {
			dagenVdWeek.add(wo);
		} else if (don == 4) {
			dagenVdWeek.add(don);
		} else if (vr == 5) {
			dagenVdWeek.add(vr);
		} else if (za == 6) {
			dagenVdWeek.add(za);
		} else if (zo == 7) {
			dagenVdWeek.add(zo);
		}

		// voor elk geselecteerde dag voer volgende query uit
		for (int i = 0; i < dagenVdWeek.size(); i++) {
			DayOfWeekIterator it = new DayOfWeekIterator(begindatum, einddatum, dagenVdWeek.get(i));

			while (it.hasNext()) {
				System.out.println("dit is it.next();" + it.next());
				datum = new Timestamp(it.next().getMillis());
				System.out.println("Dit is datum na conversie: " + datum);
				try {
					currentCon = ConnectionManager.getConnection();
					PreparedStatement insertRit;

					String queryString = ("INSERT INTO Rit ("
							+ "lidnr,"
							+ " startpunt,"
							+ " eindpunt,"
							+ " waypoint,"
							+ " afstand,"
							+ " prijs,"
							+ " gekocht,"
							+ " datum,"
							+ " zitplaatsen,"
							+ " brandstof,"
							+ " aangeboden)"
							+ " Values"
							+ "(?,?,?,?,?,?,?,?,?,?,?);");

					insertRit = currentCon.prepareStatement(queryString);

					insertRit.setInt(1, lidnr);
					insertRit.setString(2, startpunt);
					insertRit.setString(3, eindpunt);
					if (waypoints.equals("")) {
						insertRit.setString(4, null);
					} else {
						insertRit.setString(4, waypoints);
					}
					insertRit.setDouble(5, afstand);
					insertRit.setDouble(6, prijs);
					insertRit.setInt(7, gekocht);
					insertRit.setTimestamp(8, datum);
					insertRit.setInt(9, zitplaatsen);
					insertRit.setString(10, brandstof);
					insertRit.setInt(11, aangeboden);

					System.out.println("De query is: " + insertRit);
					insertRit.executeUpdate();

				} catch (SQLException ex) {
					Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
					

				}


			}
		}
		
	}

	/**
	 *
	 */
	private void saveRit() {

		datum = new Timestamp(begindatum.getMillis());
		try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement insertRit;

			String queryString = ("INSERT INTO Rit ("
					+ "lidnr,"
					+ " startpunt,"
					+ " eindpunt,"
					+ " waypoint,"
					+ " afstand,"
					+ " prijs,"
					+ " gekocht,"
					+ " datum,"
					+ " zitplaatsen,"
					+ " brandstof,"
					+ " aangeboden)"
					+ " Values"
					+ "(?,?,?,?,?,?,?,?,?,?,?);");
			insertRit = currentCon.prepareStatement(queryString);

			insertRit.setInt(1, lidnr);
			insertRit.setString(2, startpunt);
			insertRit.setString(3, eindpunt);
			if (waypoints.equals("")) {
				insertRit.setString(4, null);
			} else {
				insertRit.setString(4, waypoints);
			}
			insertRit.setDouble(5, afstand);
			insertRit.setDouble(6, prijs);
			insertRit.setInt(7, gekocht);
			insertRit.setTimestamp(8, datum);
			insertRit.setInt(9, zitplaatsen);
			insertRit.setString(10, brandstof);
			insertRit.setInt(11, aangeboden);

			System.out.println("De query is: " + insertRit);

			insertRit.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);
			success = false;
			System.out.println("Var Success = " + success);
		}
		success = true;

	}

	public static Connection getCurrentCon() {
		return currentCon;
	}

	public static void setCurrentCon(Connection currentCon) {
		RitDao.currentCon = currentCon;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		RitDao.rs = rs;
	}

	public int getLidnr() {
		return lidnr;
	}

	public void setLidnr(int lidnr) {
		this.lidnr = lidnr;
	}

	public String getStartpunt() {
		return startpunt;
	}

	public void setStartpunt(String startpunt) {
		this.startpunt = startpunt;
	}

	public String getEindpunt() {
		return eindpunt;
	}

	public void setEindpunt(String eindpunt) {
		this.eindpunt = eindpunt;
	}

	public String getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(String waypoints) {
		this.waypoints = waypoints;
	}

	public Double getAfstand() {
		return afstand;
	}

	public void setAfstand(Double afstand) {
		this.afstand = afstand;
	}

	public Double getPrijs() {
		return prijs;
	}

	public void setPrijs(Double prijs) {
		this.prijs = prijs;
	}

	public int getGekocht() {
		return gekocht;
	}

	public void setGekocht(int gekocht) {
		this.gekocht = gekocht;
	}

	public Timestamp getDatum() {
		return datum;
	}

	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}

	public DateTime getBegindatum() {
		return begindatum;
	}

	public void setBegindatum(DateTime begindatum) {
		this.begindatum = begindatum;
	}

	public DateTime getEinddatum() {
		return einddatum;
	}

	public void setEinddatum(DateTime einddatum) {
		this.einddatum = einddatum;
	}

	public int getZitplaatsen() {
		return zitplaatsen;
	}

	public void setZitplaatsen(int zitplaatsen) {
		this.zitplaatsen = zitplaatsen;
	}

	public int getAangeboden() {
		return aangeboden;
	}

	public void setAangeboden(int aangeboden) {
		this.aangeboden = aangeboden;
	}

	public String getBrandstof() {
		return brandstof;
	}

	public void setBrandstof(String brandstof) {
		this.brandstof = brandstof;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public int getDi() {
		return di;
	}

	public void setDi(int di) {
		this.di = di;
	}

	public int getWo() {
		return wo;
	}

	public void setWo(int wo) {
		this.wo = wo;
	}

	public int getDon() {
		return don;
	}

	public void setDon(int don) {
		this.don = don;
	}

	public int getVr() {
		return vr;
	}

	public void setVr(int vr) {
		this.vr = vr;
	}

	public int getZa() {
		return za;
	}

	public void setZa(int za) {
		this.za = za;
	}

	public int getZo() {
		return zo;
	}

	public void setZo(int zo) {
		this.zo = zo;
	}

	public int getMeerdere() {
		return meerdere;
	}

	public void setMeerdere(int meerdere) {
		this.meerdere = meerdere;
	}
}
