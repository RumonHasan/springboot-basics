/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import Task from './components/Task';
import { MantineProvider } from '@mantine/core';

const endPoint = 'http://localhost:8080/tasks';

const App = () => {
  const [tasks, setTasks] = useState([]);

  const getTasks = async () => {
    try {
      const { data } = await axios.get(endPoint);
      if (data.length > 0) {
        setTasks(data);
      } else {
        console.log('no tasks available');
      }
    } catch (error) {
      console.log(error);
    }
  };

  // initial render for getting the tasks
  useEffect(() => {
    getTasks();
  }, []);

  // deleting a task
  const deleteTask = async (id) => {
    console.log(id);
    try {
      const deleteConfirm = await axios.delete(`${endPoint}/delete/${id}`);
      if (deleteConfirm) {
        console.log('deleted');
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <MantineProvider>
      <div style={{ display: 'flex', flexDirection: 'column' }}>
        {tasks.map((item, id) => {
          return <Task key={id} {...item} deleteTask={deleteTask} />;
        })}
      </div>
    </MantineProvider>
  );
};

export default App;
