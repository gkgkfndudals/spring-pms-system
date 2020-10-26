package hu.schedule;

public class SchVO {
	private String ssno;	//일정번호
	private String sstitle;	//일정명
	private String sstype;	//구분
	private String ssstartdate;	//시작일
	private String ssstarthour;	//시작일-시간
	private String ssstartminute;	//시작일-분
	private String ssenddate;	//종료일
	private String ssendhour;	//종료일-시간
	private String ssendminute;	//종료일-분
	private String ssrepeattype;	//반복
	private String ssrepeattypenm;
	private String ssrepeatoption;	//반복옵션-주
	private String ssrepeatend;	//반복종료
	private String sscontents;	//내용
	private String ssisopen;	//공개여부
	private String userno;	//사용자번호
	private String usernm;
	
	public String getSsno() {
		return ssno;
	}

	public String getSstitle() {
		return sstitle;
	}

	public String getSstype() {
		return sstype;
	}

	public String getSsstartdate() {
		return ssstartdate;
	}

	public String getSsstarthour() {
		return ssstarthour;
	}

	public String getSsstartminute() {
		return ssstartminute;
	}

	public String getSsenddate() {
		return ssenddate;
	}

	public String getSsendhour() {
		return ssendhour;
	}

	public String getSsendminute() {
		return ssendminute;
	}

	public String getSsrepeattype() {
		return ssrepeattype;
	}

	public String getSsrepeatend() {
		return ssrepeatend;
	}

	public String getSscontents() {
		return sscontents;
	}

	public String getSsisopen() {
		return ssisopen;
	}

	public String getUserno() {
		return userno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public void setSstitle(String sstitle) {
		this.sstitle = sstitle;
	}

	public void setSstype(String sstype) {
		this.sstype = sstype;
	}

	public void setSsstartdate(String ssstartdate) {
		this.ssstartdate = ssstartdate;
	}

	public void setSsstarthour(String ssstarthour) {
		this.ssstarthour = ssstarthour;
	}

	public void setSsstartminute(String ssstartminute) {
		this.ssstartminute = ssstartminute;
	}

	public void setSsenddate(String ssenddate) {
		this.ssenddate = ssenddate;
	}

	public void setSsendhour(String ssendhour) {
		this.ssendhour = ssendhour;
	}

	public void setSsendminute(String ssendminute) {
		this.ssendminute = ssendminute;
	}

	public void setSsrepeattype(String ssrepeattype) {
		this.ssrepeattype = ssrepeattype;
	}

	public String getSsrepeattypenm() {
		return ssrepeattypenm;
	}

	public void setSsrepeattypenm(String ssrepeattypenm) {
		this.ssrepeattypenm = ssrepeattypenm;
	}

	public void setSsrepeatend(String ssrepeatend) {
		this.ssrepeatend = ssrepeatend;
	}

	public void setSscontents(String sscontents) {
		this.sscontents = sscontents;
	}

	public void setSsisopen(String ssisopen) {
		this.ssisopen = ssisopen;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getSsrepeatoption() {
		return ssrepeatoption;
	}

	public void setSsrepeatoption(String ssrepeatoption) {
		this.ssrepeatoption = ssrepeatoption;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
}
