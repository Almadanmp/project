import React from 'react';
import 'react-day-picker/lib/style.css';
import {fetchSensor} from "./Actions";
import {connect} from 'react-redux';
import US006Button from './US006Button';

class AreaSensorCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      geographicAreaId:this.props.geographicAreaId,
      typeSensor:this.props.typeSensor,
      name: '',
      sensorId: '',
      dateStartedFunctioning: '',
      latitude: '',
      longitude: '',
      altitude: ''
    };
  }

  handleInputChange = attribute => event => {
    this.setState({
      [attribute]: event.target.value
    });
  };


  handleSubmit(){
    this.props.onFetchSensor(this.state);
  }


  render() {
    const {sensorId, name, dateStartedFunctioning, latitude, longitude, altitude} = this.state;
    return (
      <div>
        <label>Sensor ID:
          <input value={sensorId} placeholder="Sensor00" type="text" name="sensorId" onChange={this.handleInputChange('sensorId')}/>
        </label>
        <p></p>
        <label>Sensor name:
          <input value={name} placeholder="Sensor name" type="text" name="name" onChange={this.handleInputChange('name')}/>
        </label>
        <p></p>
        <label>Date it started functioning:
          <input value={dateStartedFunctioning} placeholder="YYYY-MM-DD" type="text" name="dateStartedFunctioning" onChange={this.handleInputChange('dateStartedFunctioning')}/>
        </label>
        <p></p>

        <label>Latitude:
          <input value={latitude} placeholder="Latitude" type="number" name="latitude" onChange={this.handleInputChange('latitude')}/>
        </label>
        <p></p>
        <label>Longitude:
          <input value={longitude} placeholder="Longitude" type="number" name="longitude" onChange={this.handleInputChange('longitude')}/>
        </label>
        <p></p>
        <label>Altitude:
          <input value={altitude} placeholder="Altitude" type="number" name="altitude" onChange={this.handleInputChange('altitude')}/>
        </label>
        <US006Button geographicAreaId={this.props.geographicAreaId} typeSensor={this.props.typeSensor} sensorId={this.state.sensorId} name={this.state.name} dateStartedFunctioning={this.state.dateStartedFunctioning}
        latitude={this.props.latitude} longitude={this.props.longitude} altitude={this.props.altitude}/>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchSensor: ({geographicAreaId, typeSensor,name,sensorId,dateStartedFunctioning}) => {
      dispatch(fetchSensor({geographicAreaId, typeSensor,name,sensorId,dateStartedFunctioning}))
    }
  }
};

export default connect(null,mapDispatchToProps)(AreaSensorCreator);

