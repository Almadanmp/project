import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';
import {deleteRoomFromGrid} from "../US149/Actions";

class AttachRoomToGrid extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
  //  this.handleChange = this.handleChange.bind(this);
    this.state = {
      name: '',
      grid: this.props.grid,
      item: [],
      formerGrid:''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };
  }

  handleSubmit() {
    this.props.onDeleteRoomFromGrid(this.state.name, this.state.formerGrid)
    this.props.onAttachRoomGrid(this.state.name, this.state.grid);
  }

  // componentDidMount() {
  //   console.log(this.props.grid)
  //   const token = localStorage.getItem('loginToken');
  //   fetch('https://localhost:8443/gridSettings//grids/'+this.props.grid+'/notAttached', {
  //     headers: {
  //       'Authorization': token,
  //       "Access-Control-Allow-Credentials": true,
  //       "Access-Control-Allow-Origin": "*",
  //       "Content-Type": "application/json"
  //     }
  //   })
  //     .then(res => res.json())
  //     .then((json) => {
  //       this.setState({
  //         item: json,
  //       })
  //     })
  //     .catch(console.log)
  // }

  // handleChange(event) {
  //   this.setState({
  //     name: event.target.value,
  //     formerGrid: event.target.
  //   });
  // }

  render() {
    const {name, item} = this.state;
    return (
      <>
        {/*<Form action="" method="post" >*/}
          {/*<FormGroup>*/}
            {/*<Label>Select Room</Label>*/}
            {/*<Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>*/}
              {/*<option value="0" onChange={this.handleChange}>Please select</option>*/}
              {/*{item.map(items => (*/}
                {/*<option value={items.name}  key={items.name}>*/}
                  {/*Name: {items.name}*/}
                  {/*Grid: {items.gridID}*/}
                {/*</option>*/}
              {/*))}*/}
            {/*</Input>*/}
          {/*</FormGroup>*/}
        {/*</Form>*/}
        RoomID:<input value={this.state.name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        Former Grid:<input value={this.state.formerGrid} type="text" name="formerGrid" onChange={this.handleInputChange('formerGrid')}/>
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Attach
          Room {name} to {this.props.grid}</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onAttachRoomGrid: (name, grid) => {
      dispatch(attachRoomGrid({name, grid}))
    },
    onDeleteRoomFromGrid: (name, grid) => {
      dispatch(deleteRoomFromGrid({name, grid}))
    }
  }
};

export default connect(null, mapDispatchToProps)(AttachRoomToGrid);
