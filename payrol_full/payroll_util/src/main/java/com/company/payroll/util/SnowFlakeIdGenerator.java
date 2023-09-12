package com.company.payroll.util;

public class SnowFlakeIdGenerator {

	// start time stamp initialized at 2023-05-15 12:00:00
	private static final long START_TIMESTAMP = 1684123200000L;
	private static final long SEQUENCE_BIT = 12L;
	private static final long WORKER_ID_BIT = 5L;
	private static final long DATA_CENTER_ID_BIT = 5L;

	private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BIT);
	private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BIT);
	private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

	private static final long WORKER_ID_SHIFT = SEQUENCE_BIT;
	private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT;
	private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BIT + WORKER_ID_BIT + DATA_CENTER_ID_BIT;

	// 0 ~ 31 number for dataCenterId and workerId
	private final long dataCenterId;
	private final long workerId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;

	public SnowFlakeIdGenerator(long dataCenterId, long workerId) {
		if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
			throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_ID or less than 0");
		}

		if (workerId > MAX_WORKER_ID || workerId < 0) {
			throw new IllegalArgumentException("workerId can't be greater than MAX_WORKER_ID or less than 0");
		}

		this.dataCenterId = dataCenterId;
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long currentTimestamp = generateNewTimestamp();

		if (currentTimestamp < lastTimestamp) {
			throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
		}

		if (currentTimestamp == lastTimestamp) {
			sequence = (sequence + 1) & MAX_SEQUENCE;

			if (sequence == 0L) {
				currentTimestamp = getNextMill(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = currentTimestamp;

		return (currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT | dataCenterId << DATA_CENTER_ID_SHIFT
		        | workerId << WORKER_ID_SHIFT | sequence;
	}

	protected long getNextMill(long lastTimestamp) {
		long mill = generateNewTimestamp();

		while (mill <= lastTimestamp) {
			mill = generateNewTimestamp();
		}

		return mill;
	}

	private long generateNewTimestamp() {
		return System.currentTimeMillis();
	}
}
