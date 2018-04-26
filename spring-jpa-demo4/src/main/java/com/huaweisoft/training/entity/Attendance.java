package com.huaweisoft.training.entity;

import javax.persistence.Id;

/**
 * 考勤
 */
public class Attendance {

    @Id
	private Integer id; // 考勤ID
	private Integer employeeId; // 员工ID
	private String workDate; // 工作日期 （格式：2018-04-17）
	private String arrivalTime; // 上班时间（格式：08:40:00）
	private String leaveTime; // 下班时间（格式：19:00:00）

	public Attendance(Integer id, Integer employeeId, String workDate, String arrivalTime, String leaveTime) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.arrivalTime = arrivalTime;
		this.leaveTime = leaveTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

}