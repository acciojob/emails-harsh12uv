package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

        public class Workspace extends Gmail{

            private ArrayList<Meeting> calendar; // Stores all the meetings

            public Workspace(String emailId) {
                // The inboxCapacity is equal to the maximum value an integer can store.
                super(emailId,Integer.MAX_VALUE);
                this.calendar = new ArrayList<>();

            }

            public void addMeeting(Meeting meeting){
                //add the meeting to calendar
                calendar.add(meeting);

            }

            public int findMaxMeetings(){
                // find the maximum number of meetings you can attend
                // 1. At a particular time, you can be present in at most one meeting
                // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
                // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
                Collections.sort(calendar, (m1, m2) -> m1.getEnd().compareTo(m2.getEnd()));

                int maxMeetings = 0;
                LocalTime lastEndTime =calendar.get(0).getEnd(); // Initialize with the smallest possible value

                // Iterate through sorted meetings
                for (Meeting meeting : calendar) {
                    // If the meeting's start time is after or equal to the last meeting's end time,
                    // Attend the meeting
                    if (meeting.getStart().compareTo(lastEndTime)>1) {
                        maxMeetings++;
                        lastEndTime = meeting.getEnd();
                    }
                }

                return maxMeetings;
            }
        }
