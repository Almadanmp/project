import React, {Component} from 'react';
import RoomEditor from "./RoomEditor";

class US108Select extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      value: ''
    };
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/houseSettings/houseRooms', {
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"
        }
      }
    )
      .then(res => res.json())
      .then((json) => {
        this.setState({
          isLoaded: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }


  render() {

    const {isLoaded, item} = this.state;
    this.state.item = Array.from(this.state.item);
    if (!isLoaded) {
      return <div>Loading
        ...</div>
    } else {

      if (!item.error) {
        return (
          <div>
            <RoomEditor name={this.props.name}/>
          </div>
        );
      } else {
        return "ERROR: Non-authorized user."
      }
    }
  }
}

export default US108Select;