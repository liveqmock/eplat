package test.test.monitor;

// import ch.ethz.ssh2.Connection;
// import ch.ethz.ssh2.Session;

public class RemoteTOPMonitor implements Runnable {

	private String interval = "1";

	private UpdateListener updateListener = null;
	private Thread thread = null;

	private String hostname;
	private String username;
	private String password;

	public RemoteTOPMonitor(UpdateListener ul, String interval) {
		thread = new Thread(this);
		this.updateListener = ul;
		this.interval = interval;
	}

	public static void main(String[] args) {
		new RemoteTOPMonitor(null, "1").start();
	}

	public void start() {
		thread.start();
	}

	public void run() {
	    /*
		System.out.println("TopMonitor : Start");
		try {
			Connection conn = new Connection(hostname);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (isAuthenticated) {
				Session session = conn.openSession();
				session.execCommand("/usr/bin/top -b -d " + interval);
				StreamReader srIn = new StreamReader(session.getStdout(),
						updateListener);
				srIn.start();
				srIn.getThread().join();
				StreamReader srErr = new StreamReader(session.getStderr(), null);
				srErr.start();
				srErr.getThread().join();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        */
	    
		System.out.println("TopMonitor : End");
	}

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
