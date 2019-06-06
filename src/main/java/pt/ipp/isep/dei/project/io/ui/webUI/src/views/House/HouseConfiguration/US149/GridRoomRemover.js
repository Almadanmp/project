import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {fetchRoomFromGrid} from "./Actions";
import {connect} from 'react-redux';

class GridRoomRemover extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      roomID: '',
      gridID: '',
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  handleSubmit() {
    this.props.onfetchRoomFromGrid(this.state);
  }


  render() {
    const {roomID, gridID} = this.state;
    return (
      <>
        RoomID:<input value={this.state.roomID} type="text" name="roomID" onChange={this.handleInputChange('roomID')}/>
        GridID:<input value={this.state.gridID} type="text" name="grid" onChange={this.handleInputChange('gridID')}/>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Remove Room {roomID} from {gridID} Energy Grid</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onfetchRoomFromGrid: ({roomID, gridID}) => {
      dispatch(fetchRoomFromGrid({roomID, gridID}))
    }
  }
};

export default connect(null, mapDispatchToProps)(GridRoomRemover);

