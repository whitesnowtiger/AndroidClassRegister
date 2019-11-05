package com.example.wpls;

import android.content.Context;
import android.widget.TextView;

public class Schedule {
    private String monday[] = new String[5];
    private String tuesday[] = new String[5];
    private String wednesday[] = new String[5];
    private String thursday[] = new String[5];
    private String friday[] = new String[5];

    public Schedule() {
        for (int i = 0; i < 5; i++) {
            monday[i] = "";
            tuesday[i] = "";
            wednesday[i] = "";
            thursday[i] = "";
            friday[i] = "";

        }
    }

    public void addSchedule(String scheduleText) {
        int temp;
        //{"response":[{"classID":"1","classTime":"Tuesday:[3:10]","classTitle":"Art Studio for Kids"},{"classID":"7","classTime":"Tuesday:[4:30]","classTitle":"Junior Chefs"}]}
        if ((temp = scheduleText.indexOf("Monday")) > -1) {
            temp += 6;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "class";
                }
            }
        }

        if ((temp = scheduleText.indexOf("Tuesday")) > -1) {
            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "Class";
                }
            }
        }

        if ((temp = scheduleText.indexOf("Wednesday")) > -1) {
            temp += 9;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "Class";
                }
            }
        }

        if ((temp = scheduleText.indexOf("Thursday")) > -1) {
            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "Class";
                }
            }
        }

        if ((temp = scheduleText.indexOf("Friday")) > -1) {
            temp += 6;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "Class";
                }
            }
        }

    }

    public void addSchedule(String scheduleText, String classTitle) {
        int temp;
        //{"response":[{"classID":"1","classTime":"Tuesday:[3:10]","classTitle":"Art Studio for Kids"},{"classID":"7","classTime":"Tuesday:[4:30]","classTitle":"Junior Chefs"}]}
        if ((temp = scheduleText.indexOf("Monday")) > -1) {
            temp += 6;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = classTitle;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Tuesday")) > -1) {
            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = classTitle;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Wednesday")) > -1) {
            temp += 9;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = classTitle;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Thursday")) > -1) {
            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = classTitle;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Friday")) > -1) {
            temp += 6;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = classTitle;
                }
            }
        }

    }

    public boolean validate(String scheduleText) {
        if (scheduleText.equals("")) {
            return true;
        }
        int temp;
        if ((temp = scheduleText.indexOf("Monday")) > -1) {
            temp += 6;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals(""))
                        return false;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Tuesday")) > -1) {
            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals(""))
                        return false;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Wednesday")) > -1) {
            temp += 9;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals(""))
                        return false;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Thursday")) > -1) {
            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals(""))
                        return false;
                }
            }
        }

        if ((temp = scheduleText.indexOf("Friday")) > -1) {
            temp += 6;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals(""))
                        return false;
                }
            }

        }
        return true;
    }

    public void setting(TextView[] monday, TextView[] tuesday, TextView[] wednesday, TextView[] thursday, TextView[] friday, Context context) {
        int maxLength = 0;
        String maxString ="";

        for(int i = 0; i<5; i++) {
            if(this.monday[i].length() > maxLength){
                maxLength = this.monday[i].length();
                maxString = this.monday[i];
            }

            if(this.tuesday[i].length() > maxLength){
                maxLength = this.tuesday[i].length();
                maxString = this.tuesday[i];
            }

            if(this.wednesday[i].length() > maxLength){
                maxLength = this.wednesday[i].length();
                maxString = this.wednesday[i];
            }

            if(this.thursday[i].length() > maxLength){
                maxLength = this.thursday[i].length();
                maxString = this.thursday[i];
            }

            if(this.friday[i].length() > maxLength){
                maxLength = this.friday[i].length();
                maxString = this.friday[i];
            }

        }

        for(int i = 0; i<5; i++) {
            if(!this.monday[i].equals("")) {

                monday[i].setText(this.monday[i]);
                monday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }
            else {
                monday[i].setText(maxString);
            }

            if(!this.tuesday[i].equals("")) {
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }
            else {
                tuesday[i].setText(maxString);
            }


            if(!this.wednesday[i].equals("")) {
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }

            else {
                wednesday[i].setText(maxString);
            }


            if(!this.thursday[i].equals("")) {
                thursday[i].setText(this.thursday[i]);
                thursday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }
            else {
                thursday[i].setText(maxString);
            }


            if(!this.friday[i].equals("")) {
                friday[i].setText(this.friday[i]);
                friday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            }  else {
                friday[i].setText(maxString);
            }


           /* monday[i].resizeText();
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();*/

            /*monday[i].reAdjust();
            tuesday[i].reAdjust();
            wednesday[i].reAdjust();
            thursday[i].reAdjust();
            friday[i].reAdjust();*/
           
        }
    }


}
