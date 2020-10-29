package hu.sign;

public class SignDocVO {

    private String docno;
    private String doctitle;
    private String doccontents;
    private String docstatus;	// 문서 상태
    private String docstep;	// 결재 단
    private String dtno;	// 양식번호
    private String dttitle;	// 양식명
    private String userno;
    private String usernm;
    private String deptnm;
    private String updatedate;
    private String docsignpath;		// 결재경로문자열

	public String getDocno() {
		return docno;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public String getDoccontents() {
		return doccontents;
	}

	public String getDocstatus() {
		return docstatus;
	}

	public String getDocstep() {
		return docstep;
	}

	public String getDtno() {
		return dtno;
	}

	public String getUserno() {
		return userno;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public String getDeptnm() {
		return deptnm;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public void setDoccontents(String doccontents) {
		this.doccontents = doccontents;
	}

	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}

	public void setDocstep(String docstep) {
		this.docstep = docstep;
	}

	public void setDtno(String dtno) {
		this.dtno = dtno;
	}

	public String getDttitle() {
		return dttitle;
	}

	public void setDttitle(String dttitle) {
		this.dttitle = dttitle;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public void setDeptnm(String deptnm) {
		this.deptnm = deptnm;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getDocsignpath() {
		return docsignpath;
	}

	public void setDocsignpath(String docsignpath) {
		this.docsignpath = docsignpath;
	} 
    
}