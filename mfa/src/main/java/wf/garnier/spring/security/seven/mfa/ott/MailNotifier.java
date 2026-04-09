package wf.garnier.spring.security.seven.mfa.ott;

public interface MailNotifier {

	void notify(String title, String message, String link);

}
