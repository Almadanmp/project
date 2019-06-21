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
      dateStartedFunctioning: '',
    };
  }

    handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };


  handleSubmit(){
    this.props.onFetchSensor(this.props.link.href,this.state.typeSensor,this.state.name,this.state.sensorId,this.state.dateStartedFunctioning);
  }


  render() {
    const {sensorId, name, dateStartedFunctioning} = this.state;
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
          <US253Button roomID={this.props.roomID} typeSensor={this.props.typeSensor} sensorId={this.state.sensorId} name={this.state.name} dateStartedFunctioning={this.state.dateStartedFunctioning}/>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchSensor: (link, typeSensor,name,sensorId,dateStartedFunctioning) => {
      dispatch(fetchSensor({link, typeSensor,name,sensorId,dateStartedFunctioning}))
    }
  }
};

export default connect(null,mapDispatchToProps)(RoomSensorCreator);

