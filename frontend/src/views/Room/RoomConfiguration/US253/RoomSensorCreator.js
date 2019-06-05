import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {fetchRoom} from "./Actions";
import {connect} from 'react-redux';

class RoomSensorCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '...',
      sensorId: '...',
      units: 0,
      dateStartedFunctioning: 0,
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  handleSubmit(){
    this.props.onFetchSensor(this.state);
  }


  render() {
    const {name, sensorId, width, length, height} = this.state;
    return (
      <>
        <input value={this.state.sensorId} type="text" name="sensorId" onChange={this.handleInputChange('name')}/>
        <input value={this.state.name} type="text" name="name" onChange={this.handleInputChange('floor')}/>
        <input value={this.state.units} type="number" name="units" onChange={this.handleInputChange('width')}/>
        <input value={this.state.dateStartedFunctioning} type="number" name="dateStartedFunctioning" onChange={this.handleInputChange('length')}/>
        <p>The room has the following details: {sensorId + ', ' +  + ', ' + '.'}</p>
        <Button onClick={this.handleSubmit}>Save new room sensor configuration</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchSensor: ({name, floor, width, length, height}) => {
      dispatch(fetchRoom({name, floor, width, length, height}))
    }
  }
};

export default connect(null,mapDispatchToProps)(RoomSensorCreator);

