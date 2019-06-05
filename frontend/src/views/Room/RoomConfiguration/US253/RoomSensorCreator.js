import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {fetchSensor} from "./Actions";
import {connect} from 'react-redux';
import US253Post from './US253Post';

class RoomSensorCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      roomID:'',
      typeSensor:'',
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
        <input value={sensorId} type="text" name="sensorId" onChange={this.handleInputChange('sensorId')}/>
        <input value={name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        <input value={dateStartedFunctioning} type="text" name="dateStartedFunctioning" onChange={this.handleInputChange('dateStartedFunctioning')}/>
        <p>The sensor has the following details: {'roomID: '+this.props.roomID+'sensor type: '+this.props.typeSensor+' '+sensorId + ', ' + name + ', ' + dateStartedFunctioning}</p>
        <Button >Save new room sensor configuration</Button>
        <US253Post roomID={this.props.roomID} typeSensor={this.props.typeSensor} sensorId={this.state.typeSensor} name={this.state.name} dateStartedFunctioning={this.state.dateStartedFunctioning}/>
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

