package test.test.monitor;

public interface DataParser {
	public String[] getCPUData(String line);

	public String[] getMemoryData(String line);

	public boolean isCPULine(String line);

	public boolean isMemoryLine(String line);
}
