/*
 * Copyright 2018 trivago N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trivago.cluecumber.rendering.pages.pojos.pagecollections;

import com.trivago.cluecumber.constants.PluginSettings;
import com.trivago.cluecumber.constants.Status;
import com.trivago.cluecumber.json.pojo.Element;
import com.trivago.cluecumber.json.pojo.Report;
import com.trivago.cluecumber.json.pojo.Tag;
import com.trivago.cluecumber.rendering.RenderingUtils;
import com.trivago.cluecumber.rendering.pages.pojos.CustomParameter;
import com.trivago.cluecumber.rendering.pages.pojos.Feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllScenariosPageCollection extends PageCollection {
    private List<Report> reports = new ArrayList<>();
    private List<CustomParameter> customParameters;
    private Tag tagFilter;
    private Feature featureFilter;

    public AllScenariosPageCollection() {
        super(PluginSettings.SCENARIO_SUMMARY_PAGE_NAME);
    }

    public List<Report> getReports() {
        return reports;
    }

    public void clearReports() {
        reports = new ArrayList<>();
    }

    public void addReports(final Report[] reportList) {
        if (reportList == null) {
            return;
        }
        this.reports.addAll(Arrays.asList(reportList));
    }

    public int getTotalNumberOfScenarios() {
        return reports.stream().map(Report::getElements).
                mapToInt(elements -> (int) elements.stream().filter(Element::isScenario).count()).sum();
    }

    public boolean hasFailedScenarios() {
        return getTotalNumberOfFailedScenarios() > 0;
    }

    public boolean hasPassedScenarios() {
        return getTotalNumberOfPassedScenarios() > 0;
    }

    public boolean hasSkippedScenarios() {
        return getTotalNumberOfSkippedScenarios() > 0;
    }

    public int getTotalNumberOfPassedScenarios() {
        return getNumberOfScenariosWithStatus(Status.PASSED);
    }

    public int getTotalNumberOfFailedScenarios() {
        return getNumberOfScenariosWithStatus(Status.FAILED);
    }

    public int getTotalNumberOfSkippedScenarios() {
        return getNumberOfScenariosWithStatus(Status.SKIPPED);
    }

    private int getNumberOfScenariosWithStatus(final Status status) {
        return reports.stream().mapToInt(
                report -> (int) report.getElements().stream().filter(
                        element -> element.getStatus().equals(status)
                ).count()).sum();
    }

    public long getTotalDuration() {
        long totalDurationNanoseconds = 0;
        for (Report report : reports) {
            totalDurationNanoseconds += report.getTotalDuration();
        }
        return totalDurationNanoseconds;
    }

    public String getTotalDurationString() {
        return RenderingUtils.convertNanosecondsToTimeString(getTotalDuration());
    }

    public List<CustomParameter> getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(final List<CustomParameter> customParameters) {
        this.customParameters = customParameters;
    }

    public boolean hasCustomParameters() {
        return customParameters != null && !customParameters.isEmpty();
    }

    public Tag getTagFilter() {
        return tagFilter;
    }

    public void setTagFilter(final Tag tagFilter) {
        this.tagFilter = tagFilter;
    }

    public Feature getFeatureFilter() {
        return featureFilter;
    }

    public void setFeatureFilter(final Feature featureFilter) {
        this.featureFilter = featureFilter;
    }
}
