package com.caravan.senior_project.my_routes;

import com.mapbox.services.api.directions.v5.models.IntersectionLanes;
import com.mapbox.services.api.directions.v5.models.StepIntersection;
import com.mapbox.services.commons.models.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohin_000 on 5/17/2017.
 */

public class MyStepIntersection {
    private List<Double> location = new ArrayList<Double>();
    private List<Integer> bearings = new ArrayList<Integer>();
    private List<Boolean> entry = new ArrayList<Boolean>();
    private int in;
    private int out;
    private List<MyIntersectionLanes> lanes = new ArrayList<MyIntersectionLanes>();

    /**
     * Empty constructor
     *
     * @since 2.0.0
     */
    public MyStepIntersection() {
    }

    /**
     * Constructor allowing the setting of the {@link IntersectionLanes}.
     *
     * @param lanes an Array of {@link IntersectionLanes}.
     * @since 2.0.0
     */
    public MyStepIntersection(List<MyIntersectionLanes> lanes) {
        this.lanes = lanes;
    }

    public MyStepIntersection(StepIntersection intersection) {
        this.location.add(intersection.getLocation()[0]);
        this.location.add(intersection.getLocation()[1]);
        for (int i = 0; i < intersection.getBearings().length; i++)
            this.bearings.add(intersection.getBearings()[i]);
        for (int i = 0; i < intersection.getEntry().length; i++)
            this.entry.add(intersection.getEntry()[i]);
        this.in = intersection.getIn();
        this.out = intersection.getOut();

        for (int i = 0; intersection.getLanes() != null && i < intersection.getLanes().length; i++)
            this.lanes.add(new MyIntersectionLanes(intersection.getLanes()[i]));

    }

    /**
     * A double array of length 2, first position being the longitude and the other being latitude.
     *
     * @return A [longitude, latitude] pair describing the location of the turn.
     * @since 1.3.0
     */
    public List<Double> getLocation() {
        return location;
    }

    /**
     * A double array of length 2, first position being the longitude and the other being latitude.
     *
     * @param location array with the order [longitude, latitude].
     * @since 2.1.0
     */
    public void setLocation(List<Double> location) {
        this.location = location;
    }


    /**
     * An integer array of bearing values available at the step intersection.
     *
     * @return An array of bearing values (for example [0,90,180,270]) that are available at the
     * intersection. The bearings describe all available roads at the intersection.
     * @since 1.3.0
     */
    public List<Integer> getBearings() {
        return bearings;
    }

    /**
     * An integer array of bearing values available at the step intersection.
     *
     * @param bearings an array of bearing values (for example [0,90,180,270]) that are available at the
     *                 intersection. The bearings describe all available roads at the intersection.
     * @since 2.1.0
     */
    public void setBearings(List<Integer> bearings) {
        this.bearings = bearings;
    }

    /**
     * An array of entry flags, corresponding in a 1:1 relationship to the bearings.
     *
     * @return An array of entry flags, corresponding in a 1:1 relationship to the bearings. A value
     * of true indicates that the respective road could be entered on a valid route. false
     * indicates that the turn onto the respective road would violate a restriction.
     * @since 1.3.0
     */
    public List<Boolean> getEntry() {
        return entry;
    }

    /**
     * An array of entry flags, corresponding in a 1:1 relationship to the bearings.
     *
     * @param entry an array of entry flags, corresponding in a 1:1 relationship to the bearings. A value
     *              of true indicates that the respective road could be entered on a valid route. false
     *              indicates that the turn onto the respective road would violate a restriction.
     * @since 2.1.0
     */
    public void setEntry(List<Boolean> entry) {
        this.entry = entry;
    }

    /**
     * Index into bearings/entry array.
     *
     * @return Index into bearings/entry array. Used to calculate the bearing before the turn.
     * Namely, the clockwise angle from true north to the direction of travel before the
     * maneuver/passing the intersection. To get the bearing in the direction of driving,
     * the bearing has to be rotated by a value of 180. The value is not supplied for departure
     * maneuvers.
     * @since 1.3.0
     */
    public int getIn() {
        return in;
    }

    /**
     * Index into bearings/entry array.
     *
     * @param in index into bearings/entry array. Used to calculate the bearing before the turn.
     *           Namely, the clockwise angle from true north to the direction of travel before the
     *           maneuver/passing the intersection. To get the bearing in the direction of driving,
     *           the bearing has to be rotated by a value of 180. The value is not supplied for departure
     *           maneuvers.
     * @since 2.1.0
     */
    public void setIn(int in) {
        this.in = in;
    }

    /**
     * Index out of the bearings/entry array.
     *
     * @return index out of the bearings/entry array. Used to extract the bearing after the turn.
     * Namely, The clockwise angle from true north to the direction of travel after the
     * maneuver/passing the intersection. The value is not supplied for arrive maneuvers.
     * @since 1.3.0
     */
    public int getOut() {
        return out;
    }

    /**
     * Index out of the bearings/entry array.
     *
     * @param out index out of the bearings/entry array. Used to extract the bearing after the turn.
     *            Namely, The clockwise angle from true north to the direction of travel after the
     *            maneuver/passing the intersection. The value is not supplied for arrive maneuvers.
     * @since 2.1.0
     */
    public void setOut(int out) {
        this.out = out;
    }

    /**
     * Converts double array {@link #getLocation()} to a {@link Position}. You'll typically want to
     * use this format instead of {@link #getLocation()} as it's easier to work with.
     *
     * @return {@link Position}.
     * @since 1.3.0
     */
    public Position asPosition() {
        return Position.fromCoordinates(location.get(0), location.get(1));
    }

    /**
     * Array of lane objects that represent the available turn lanes at the intersection.
     *
     * @return array of lane objects that represent the available turn lanes at the intersection. If
     * no lane information is available for an intersection, the lanes property will not be present.
     * Lanes are provided in their order on the street, from left to right.
     * @since 2.0.0
     */
    public List<MyIntersectionLanes> getLanes() {
        return lanes;
    }

    /**
     * Array of lane objects that represent the available turn lanes at the intersection.
     *
     * @param lanes an array of lane objects that represent the available turn lanes at the intersection. If
     *              no lane information is available for an intersection, the lanes property will not be present.
     *              Lanes are provided in their order on the street, from left to right.
     * @since 2.1.0
     */
    public void setLanes(List<MyIntersectionLanes> lanes) {
        this.lanes = lanes;
    }

    public StepIntersection intersectionToStepIntersection() {
        StepIntersection intersection = new StepIntersection();

        double[] l = new double[location.size()];
        for (int i = 0; i < location.size(); i++)
            l[i] = location.get(i);
        intersection.setLocation(l);

        int[] b = new int[bearings.size()];
        for (int i = 0; i < bearings.size(); i++)
            b[i] = bearings.get(i);
        intersection.setBearings(b);

        boolean[] e = new boolean[entry.size()];
        for (int i = 0; i < entry.size(); i++)
            e[i] = entry.get(i);
        intersection.setEntry(e);

        intersection.setIn(in);
        intersection.setOut(out);

        IntersectionLanes[] lanes1 = new IntersectionLanes[lanes.size()];
        for (int i = 0; i < lanes.size(); i++)
            lanes1[i] = lanes.get(i).myLanesToIntersectionLanes();
        intersection.setLanes(lanes1);
        return intersection;
    }
}
