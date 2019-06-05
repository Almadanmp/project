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
      isHidden: true,
      roomId: '...',
      gridId: '...',
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

  toggleHidden = () => this.setState((prevState) => ({isHidden: !prevState.isHidden}))


  render() {
    const {roomId, gridId} = this.state;
    return (
      <>

        <label> RoomID:
          <input value={this.state.roomId} type="text" name="roomId" onChange={this.handleInputChange('roomId')}/>
        </label>

        <label> GridID:
          <input value={this.state.gridId} type="text" name="gridId" onChange={this.handleInputChange('gridId')}/>
        </label>
        {' '}
        <Button onClick={this.handleSubmit && this.toggleHidden}>Remove Room {roomId} from Grid {gridId}'</Button>
        {!this.state.isHidden && <p>The room {roomId} has been successfully removed from the following grid {gridId}</p>
        }

      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onfetchRoomFromGrid: ({roomId, gridId}) => {
      dispatch(fetchRoomFromGrid({roomId, gridId}))
    }
  }
};

export default connect(null, mapDispatchToProps)(GridRoomRemover);

