import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {fetchRoom} from "./Actions";
import {connect} from 'react-redux';
import {confirmAlert} from "react-confirm-alert";

class RoomCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '',
      floor: '',
      width: '',
      length: '',
      height: '',
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };

  }

  handleSubmit() {
    this.props.onFetchRoom(this.state);
  }

  submit = () => {
    confirmAlert({
      title: 'Confirm your room configuration',
      message: 'The new room has the following new configuration: Name: ' + this.state.name + ' Floor: ' + this.state.floor + '. | Width: ' + this.state.width + 'm. | Length: ' + this.state.length + 'm. | Height: ' + this.state.height + 'm. Do you want to proceed?',
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

  render() {
    const {name, floor, width, length, height} = this.state;
    return (
      <>
        <label> Name:
          <input value={this.state.name} placeholder={"Room name"} type="text" name="name"
                 onChange={this.handleInputChange('name')}/>
        </label>
        <p></p>
        <label> Floor:
          <input value={this.state.floor} placeholder={"0"} type="number" name="floor"
                 onChange={this.handleInputChange('floor')}/>
        </label>
        <p></p>
        <label> Width:
          <input value={this.state.width} placeholder={"0"} type="number" min="0" name="width"
                 onChange={this.handleInputChange('width')}/>
        </label>
        <p></p>
        <label> Length:
          <input value={this.state.length} placeholder={"0"} type="number" min="0" name="length"
                 onChange={this.handleInputChange('length')}/>
        </label>
        <p></p>

        <label> Height:
          <input value={this.state.height} placeholder={"0"} type="number" min="0" name="height"
                 onChange={this.handleInputChange('height')}/>
        </label>
        <p></p>

        <p>The room to be created has the following
          details: {name + ', ' + floor + ', ' + width + ', ' + length + ', ' + height + '.'}</p>
        <p></p>

        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.submit}>Save new room
          configuration</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchRoom: ({name, floor, width, length, height}) => {
      dispatch(fetchRoom({name, floor, width, length, height}))
    }
  }
};

export default connect(null, mapDispatchToProps)(RoomCreator);

