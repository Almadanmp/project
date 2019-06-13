import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {deleteSensorFromArea} from "../US011/Actions";
import {connect} from 'react-redux';
import {confirmAlert} from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css


class AreaSensorRemover extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      id: 0,
      sensorId: '',
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };

  }

  submit = () => {
    confirmAlert({
      title: 'Confirm to remove',
      message: 'Are you sure to remove ' + this.state.sensorId + ' from ' + this.state.id + '?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => this.handleSubmit()
        },
        {
          label: 'No',
          onClick: () => {
          }
        }
      ]
    });
  };

  handleSubmit() {
    this.props.onDeleteSensorFromArea(this.state);
  }

  render() {
    return (
      <>

        <label> Geographic area Id:
          <input value={this.state.id} type="number" name="id" onChange={this.handleInputChange('id')}/>
        </label>

        <label> Area Sensor Id:
          <input value={this.state.sensorId} type="text" name="sensorId" onChange={this.handleInputChange('sensorId')}/>
        </label>

        <Button style={{backgroundColor: '#ffffff', marginBottom: '1rem'}} onClick={this.submit}>remove </Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onDeleteSensorFromArea: ({id, sensorId}) => {
      dispatch(deleteSensorFromArea({id, sensorId}))
    }
  }
};

export default connect(null, mapDispatchToProps)(AreaSensorRemover);
