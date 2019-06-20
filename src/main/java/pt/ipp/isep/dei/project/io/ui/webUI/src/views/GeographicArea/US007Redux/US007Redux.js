import React from 'react';
import {connect} from 'react-redux';
import {fetchMotherChild} from './Actions007';
import {Button, Card, CardBody, Collapse, Form, FormGroup, Input, Label} from "reactstrap";
import AlterMotherChild from './AlterMotherChild'


class US007Redux extends React.Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange1 = this.handleChange1.bind(this);
    this.handleChange2 = this.handleChange2.bind(this);
    this.state = {
      isHidden: true,
      item: [],
      motherId: '',
      childId: ''
    };
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/geoAreas/', {
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

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  handleChange1(event) {
    this.setState({motherId: event.target.value});
  }

  handleChange2(event) {
    this.setState({childId: event.target.value});
  }

  handleSubmit() {
    this.props.onFetchMotherChild(this.state);
    this.setState({isHidden: false})
  }

  render() {
    const {item} = this.state;

    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Add Geographic Area
          into another Geographic Area</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <Form action="" method="post">
                <FormGroup>
                  <Label>Select Geographic Area</Label>
                  <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange1}>
                    <option value="0" onChange={this.handleChange1}>Please select the mother Geographic Area</option>
                    {item.map(items => (
                      <option value={items.geographicAreaId} key={items.geographicAreaId}>
                        {items.name}
                      </option>
                    ))}
                  </Input>
                </FormGroup>
              </Form>
              <p></p>
              <Form action="" method="post">
                <FormGroup>
                  <Label>Select Geographic Area</Label>
                  <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange2}>
                    <option value="0" onChange={this.handleChange2}>Please select the child Geographic Area</option>
                    {item.map(items => (
                      <option value={items.geographicAreaId} key={items.geographicAreaId}>
                        {items.name}
                      </option>
                    ))}
                  </Input>
                </FormGroup>
              </Form>
              <p></p>
              <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Save new
                location
              </Button>
              {this.state.isHidden === false ?
                <AlterMotherChild motherId={this.state.motherId} childId={this.state.childId}/> : ''}
            </CardBody>
          </Card>
        </Collapse>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducers007.loading,
    location: state.Reducers007.location,
    error: state.Reducers007.error
  }
};

const
  mapDispatchToProps = (dispatch) => {
    return {
      onFetchMotherChild: ({motherId, childId}) => {
        dispatch(fetchMotherChild({motherId, childId}))
      }
    }
  };

export default connect(mapStateToProps, mapDispatchToProps)(US007Redux);
