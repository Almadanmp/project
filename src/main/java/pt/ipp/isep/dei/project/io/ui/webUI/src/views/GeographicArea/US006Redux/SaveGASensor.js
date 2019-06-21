import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";
import {connect} from 'react-redux';

class SaveGASensor extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  render() {
    const {addedSensor, error} = this.props;
    console.log(addedSensor)
    const {geographicAreaId, typeSensor, name, sensorId, dateStartedFunctioning, latitude, longitude, altitude} = this.props;

    if ((addedSensor.toString()).indexOf("422") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please complete every field before continuing.</Alert></div>
        </div>
      )
    } else {
      return (
        <div className="help-block"><Alert color="success">
          <p>The following sensor was added:</p>
          <ul>
            <li>
              Name: {name}, ID: {sensorId}
            </li>
            <li>
              Type: {typeSensor}
            </li>
            <li>
              Geographic Area ID: {geographicAreaId}
            </li>
            <li>
              Start Date: {dateStartedFunctioning}
            </li>
            <li>
              Latitude: {latitude}
            </li>
            <li>
              Longitude: {longitude}
            </li>
            <li>
              Altitude: {altitude}
            </li>
          </ul>
        </Alert></div>
      );
    }

  }
}


const mapStateToProps = (state) => {
  return {
    loading: state.Reducers006.loading,
    addedSensor: state.Reducers006.addedSensor,
    error: state.Reducers006.error
  }
};


export default connect(mapStateToProps, null)(SaveGASensor);
