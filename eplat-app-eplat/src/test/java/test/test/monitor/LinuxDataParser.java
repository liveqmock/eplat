package test.test.monitor;

public class LinuxDataParser implements DataParser {

	public String[] getCPUData(String line) {
		String cpuStr = line.substring(line.indexOf("Cpu(s): ") + 8);
		int indexOfUser = cpuStr.indexOf("%us,");
		String user = cpuStr.substring(0, indexOfUser).trim();
		int indexOfSys = cpuStr.indexOf("%sy,");
		String sys = cpuStr.substring(indexOfUser + 5, indexOfSys).trim();
		int indexOfIdle = cpuStr.indexOf("%id,");
		int indexOfNi = cpuStr.indexOf("%ni,");
		String idle = cpuStr.substring(indexOfNi + 5, indexOfIdle).trim();

		return new String[] { user, sys, idle };
	}

	public String[] getMemoryData(String line) {
		String memStr = line.substring(line.indexOf("total, ") + 7);
		int indexOfUsed = memStr.indexOf("k used,");
		String used = memStr.substring(0, indexOfUsed).trim();
		double usedN = Double.parseDouble(used);
		used = "" + (usedN / 1024.0);
		int indexOfFree = memStr.indexOf("k free,");
		String free = "0.5";
		if (indexOfFree >= 0) {
			free = memStr.substring(indexOfUsed + 7, indexOfFree).trim();
			double freeN = Double.parseDouble(free);
			free = "" + (freeN / 1024.0);
		}
		return new String[] { used, free };
	}

	public boolean isCPULine(String line) {
		return line.startsWith("Cpu(s): ");
	}

	public boolean isMemoryLine(String line) {
		return line.startsWith("Mem: ");
	}
}
