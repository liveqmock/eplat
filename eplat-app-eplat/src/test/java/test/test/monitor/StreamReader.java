package test.test.monitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.application.Platform;

public class StreamReader implements Runnable {

	private UpdateListener updateListener;
	private InputStream in = null;
	private Thread thread = null;
	private DataParser dataParser = null;

	public StreamReader(InputStream in, UpdateListener ul) {
		thread = new Thread(this);
		this.in = in;
		updateListener = ul;
		dataParser = new LinuxDataParser();
	}

	public void start() {
		thread.start();
	}

	public void run() {
		try {
			//
			// CPU usage: 13.94% user, 7.21% sys, 78.85% idle
			// PhysMem: 982M wired, 1175M active, 1617M inactive, 4030M used,
			// 66M free.
			//
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			String line = reader.readLine();
			while (line != null) {
				if (updateListener == null) {
					System.out.println(line);
				} else {
					update(line);
				}
				line = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(String line) {
		try {
			if (dataParser.isCPULine(line)) {
				final String[] data = dataParser.getCPUData(line);
				Platform.runLater(new Runnable() {
					public void run() {
						updateListener.updateCPU(data[0], data[1], data[2]);
					}
				});
			} else if (dataParser.isMemoryLine(line)) {
				final String[] data = dataParser.getMemoryData(line);
				Platform.runLater(new Runnable() {
					public void run() {
						updateListener.updateMemory(data[0], data[1]);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
