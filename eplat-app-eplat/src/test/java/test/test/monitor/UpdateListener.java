package test.test.monitor;

public interface UpdateListener {
	public void updateCPU(String user, String sys, String idle);

	public void updateMemory(String used, String free);
}
