import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {deleteRoomFromGrid} from "../../US149/Actions";
import {connect} from 'react-redux';
import TableBody from "../TableBody";

class removeFromGrid extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      roomID: '',
      gridID: '',
    };
  }


  handleSubmit() {
    this.props.onDeleteRoomFromGrid(this.state);
  }

  render() {
    const {roomID, gridID} = this.state;
    return (
      <>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Remove
          Room {roomID} from {gridID} Energy Grid</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onDeleteRoomFromGrid: ({roomID, gridID}) => {
      dispatch(deleteRoomFromGrid({roomID, gridID}))
    }
  }
};

export default connect(null, mapDispatchToProps)(removeFromGrid);

