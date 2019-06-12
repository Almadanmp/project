import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {deleteRoomFromGrid} from "../../US149/Actions";
import {connect} from 'react-redux';

class RemoveFromGrid extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: this.props.name,
      grid: this.props.grid,
    };
  }


  handleSubmit() {
    this.props.onDeleteRoomFromGrid(this.state);
  }

  render() {
    console.log(this.props.name,this.props.grid)
    const {name, grid} = this.state;
    return (
      <>
        <Button style={{backgroundColor: '#ffffff', marginBottom: '1rem'}} onClick={this.handleSubmit()}><i
          class="fa fa-minus-square-o fa-lg"></i> </Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onDeleteRoomFromGrid: ({name, grid}) => {
      dispatch(deleteRoomFromGrid({name, grid}))
    }
  }
};

export default connect(null, mapDispatchToProps)(RemoveFromGrid);
