import React from 'react';
import 'react-day-picker/lib/style.css';
import {fetchSensor} from "./Actions";
import {connect} from 'react-redux';
import US253Button from './US253Button';

class RoomSensorCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      roomID:this.props.roomID,
      typeSensor:this.props.typeSensor,
      name: '',
      sensorId: '',
      dateStartedFunctioning: "YYYY-MM-DD",
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
    const {sensorId, name, dateStartedFunctioning} = this.state;
    return (
      <div>
        <label>Sensor ID:
        <input value={sensorId} type="text" name="sensorId" onChange={this.handleInputChange('sensorId')}/>
        </label>
        <p></p>
        <label>Sensor name:
          <input value={name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        </label>
          <p></p>
        <label>Date it started functioning:
        <input value={dateStartedFunctioning} type="text" name="dateStartedFunctioning" onChange={this.handleInputChange('dateStartedFunctioning')}/>
        </label>
          <US253Button roomID={this.props.roomID} typeSensor={this.props.typeSensor} sensorId={this.state.sensorId} name={this.state.name} dateStartedFunctioning={this.state.dateStartedFunctioning}/>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchSensor: ({roomID, typeSensor,name,sensorId,dateStartedFunctioning}) => {
      dispatch(fetchSensor({roomID, typeSensor,name,sensorId,dateStartedFunctioning}))
    }
  }
};

export default connect(null,mapDispatchToProps)(RoomSensorCreator);

